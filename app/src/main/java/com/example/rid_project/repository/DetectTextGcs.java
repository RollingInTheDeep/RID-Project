package com.example.rid_project.repository;

import android.util.Log;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.ImageAnnotatorSettings;
import com.google.cloud.vision.v1.ImageSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DetectTextGcs {

    // Detects text in the specified remote image on Google Cloud Storage.
    public static void detectTextGcs(FixedCredentialsProvider f) throws IOException {

        List<AnnotateImageRequest> requests = new ArrayList<>();

        String gcsPath = "gs://rid_bucket/a.jpg";


        ImageSource imgSource = ImageSource.newBuilder().setGcsImageUri(gcsPath).build();
        Image img = Image.newBuilder().setSource(imgSource).build();

        Feature feat = Feature.newBuilder().setType(Feature.Type.TEXT_DETECTION).build();

        AnnotateImageRequest request =
                AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
        requests.add(request);

        Log.e("test", "2");


        ImageAnnotatorSettings imageAnnotatorSettings = null;
        ImageAnnotatorClient client = null;

        try {

            imageAnnotatorSettings = ImageAnnotatorSettings.newBuilder()
                    .setCredentialsProvider(f).build();
            client = ImageAnnotatorClient.create(imageAnnotatorSettings);

            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
            List<AnnotateImageResponse> responses = response.getResponsesList();

            Log.e("test", "1");
            for (AnnotateImageResponse res : responses) {
                if (res.hasError()) {
                    System.out.format("Error: %s%n", res.getError().getMessage());
                    return ;
                }

                // For full list of available annotations, see http://g.co/cloud/vision/docs
                for (EntityAnnotation annotation : res.getTextAnnotationsList()) {
                    System.out.format("Text: %s%n", annotation.getDescription());
                    System.out.format("Position : %s%n", annotation.getBoundingPoly());
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
