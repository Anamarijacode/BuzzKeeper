package com.opgkukic.buzzkeeper.ui.fragments;

import android.Manifest;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.camera.core.AspectRatio;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.common.util.concurrent.ListenableFuture;
import com.opgkukic.buzzkeeper.R;
import com.opgkukic.buzzkeeper.ui.activities.HomeActivity;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CameraFragment extends Fragment {

    private static final String TAG = "CameraXFragment";
    private static final String FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS";

    private PreviewView viewFinder;
    private Button captureButton;

    private ImageCapture imageCapture;
    private ExecutorService cameraExecutor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Omogućavanje full-screen moda
        if (getActivity() instanceof HomeActivity) {
            ((HomeActivity) getActivity()).hideBottomNavigationView();
        }
        requireActivity().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
        return inflater.inflate(R.layout.fragment_camera, container, false);
    }
    @Override
    public void onResume() {
        super.onResume();

        // Ponovno omogućavanje full-screen moda kada korisnik uđe u fragment
        requireActivity().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
        if (getActivity() instanceof HomeActivity) {
            ((HomeActivity) getActivity()).hideBottomNavigationView();
        }
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewFinder = view.findViewById(R.id.viewFinder);
        captureButton = view.findViewById(R.id.image_capture_button);

        cameraExecutor = Executors.newSingleThreadExecutor();

        if (hasCameraPermission()) {
            startCamera();
        } else {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, 200);
        }

        captureButton.setOnClickListener(v -> capturePhoto());
    }

    private boolean hasCameraPermission() {
        return ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) ==
                android.content.pm.PackageManager.PERMISSION_GRANTED;
    }

    private void startCamera() {
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext());

        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();

                Preview preview = new Preview.Builder()
                        .setTargetAspectRatio(AspectRatio.RATIO_4_3)
                        .build();

                CameraSelector cameraSelector = new CameraSelector.Builder()
                        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                        .build();

                imageCapture = new ImageCapture.Builder()
                        .setTargetAspectRatio(AspectRatio.RATIO_4_3)
                        .build();

                preview.setSurfaceProvider(viewFinder.getSurfaceProvider());

                cameraProvider.unbindAll();
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture);

            } catch (ExecutionException | InterruptedException e) {
                Log.e(TAG, "Error starting camera", e);
            }
        }, ContextCompat.getMainExecutor(requireContext()));
    }

    private void capturePhoto() {
        if (imageCapture == null) return;

        String name = new SimpleDateFormat(FILENAME_FORMAT, Locale.US)
                .format(System.currentTimeMillis());

        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, name);
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
            contentValues.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image");
        }

        ImageCapture.OutputFileOptions outputOptions = new ImageCapture.OutputFileOptions
                .Builder(requireContext().getContentResolver(),
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues)
                .build();

        imageCapture.takePicture(outputOptions, cameraExecutor, new ImageCapture.OnImageSavedCallback() {
            @Override
            public void onImageSaved(ImageCapture.OutputFileResults outputFileResults) {
                Uri savedUri = outputFileResults.getSavedUri();
                if (savedUri != null) {
                    Log.d(TAG, "Photo saved: " + savedUri);

                    DodajKosnicuView2Fragment dodajKosnicuView2Fragment = new DodajKosnicuView2Fragment();
                    Bundle result = new Bundle();
                    result.putString("image_uri", savedUri.toString()); // Prosleđivanje URI-ja
                    dodajKosnicuView2Fragment.setArguments(result);


                    requireActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, dodajKosnicuView2Fragment)
                            .addToBackStack(null)
                            .commit();

                }
            }

            @Override
            public void onError(ImageCaptureException exception) {
                Log.e(TAG, "Photo capture failed: " + exception.getMessage(), exception);
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (cameraExecutor != null) {
            cameraExecutor.shutdown();
        }
    }
}
