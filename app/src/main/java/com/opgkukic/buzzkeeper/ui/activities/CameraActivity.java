package com.opgkukic.buzzkeeper.ui.activities;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.AspectRatio;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.common.util.concurrent.ListenableFuture;
import com.opgkukic.buzzkeeper.R;
import com.opgkukic.buzzkeeper.ui.fragments.DodajKosnicuFragment;
import com.opgkukic.buzzkeeper.ui.fragments.DodajKosnicuView2Fragment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CameraActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_CAMERA_PERMISSION = 200;
    private static final String[] PERMISSIONS = {Manifest.permission.CAMERA};

    private static final String TAG = "CameraXBasic";
    private static final String FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS";

    Button camera_capture_button;
    PreviewView view_finder;
    ExecutorService executor;
    ImageCapture imageCapture;

    ListenableFuture<ProcessCameraProvider> cameraProviderFuture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        camera_capture_button = findViewById(R.id.image_capture_button);
        view_finder = findViewById(R.id.viewFinder);

        if (checkPermission()) {
            startCamera();
        } else {
            ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST_CODE_CAMERA_PERMISSION);
        }

        // OnClickListener za snimanje slike
        camera_capture_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File photoFile = new File(getBaseContext().getExternalCacheDir() + File.separator + System.currentTimeMillis() + ".png");
                String name = new SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(System.currentTimeMillis());

                ContentValues contentValues = new ContentValues();
                contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, name);
                contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                    contentValues.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image");
                }

                ImageCapture.OutputFileOptions outputOptions = new ImageCapture.OutputFileOptions.Builder(getContentResolver(), MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues).build();
                imageCapture.takePicture(outputOptions, executor,
                        new ImageCapture.OnImageSavedCallback() {
                            @Override
                            public void onImageSaved(ImageCapture.OutputFileResults outputFileResults) {
                                if (outputFileResults != null) {
                                    Uri savedUri = outputFileResults.getSavedUri();
                                    if (savedUri == null) {
                                        savedUri = Uri.fromFile(photoFile);
                                    }

                                    Intent resultIntent = new Intent();
                                    resultIntent.putExtra("image_uri", savedUri.toString());
                                    setResult(RESULT_OK, resultIntent);

                                    // Zaustavi kameru prije nego što prikažeš fragment
                                    stopCamera();

                                    // Prikaži fragment
                                    showDodajKosnicuFragment(savedUri);

                                } else {
                                    Log.e(TAG, "OutputFileResults je null");
                                }
                            }


                            @Override
                            public void onError(ImageCaptureException exception) {
                                Log.e(TAG, "Greška u spremanju slike: " + exception.getMessage());
                            }
                        });
            }
        });

    }

    private boolean checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST_CODE_CAMERA_PERMISSION);
            return false;
        }
        return true;
    }

    private void startCamera() {
        cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                bindPreview(cameraProvider);
            } catch (ExecutionException | InterruptedException e) {
                Log.e(TAG, "Error in camera startup", e);
            }
        }, ContextCompat.getMainExecutor(this));
    }

    void bindPreview(ProcessCameraProvider cameraProvider) {
        Preview preview = new Preview.Builder()
                .setTargetAspectRatio(AspectRatio.RATIO_4_3)
                .build();

        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        executor = Executors.newSingleThreadExecutor();
        imageCapture = new ImageCapture.Builder().build();
        cameraProvider.unbindAll();
        Camera camera = cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture);
        preview.setSurfaceProvider(view_finder.getSurfaceProvider());
    }

    private void stopCamera() {
        if (cameraProviderFuture != null) {
            cameraProviderFuture.addListener(() -> {
                try {
                    ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                    cameraProvider.unbindAll(); // Oslobađa sve vezane resurse kamere
                } catch (ExecutionException | InterruptedException e) {
                    Log.e(TAG, "Error in stopping the camera", e);
                }
            }, ContextCompat.getMainExecutor(this));
        }
    }

    private void showDodajKosnicuFragment(Uri imageUri) {
        // Ne pozivaj stopCamera ovdje
        DodajKosnicuView2Fragment fragment = new DodajKosnicuView2Fragment();

        // Prosljeđivanje URI slike u fragment
        Bundle bundle = new Bundle();
        bundle.putString("image_uri", imageUri.toString());
        fragment.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment) // Zamjena fragmenta
                .commit();
    }


}
