package com.example.moniyue.model.util;

import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class FrescoUtil {
    public static void setTu(String url, SimpleDraweeView simpleDraweeView){
        Uri parse = Uri.parse(url);
        simpleDraweeView.setImageURI(parse);
    }
    public static void setJianJin(String url, SimpleDraweeView simpleDraweeView){
        Uri uri = Uri.parse(url);
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
            .setProgressiveRenderingEnabled(true)
            .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
            .setImageRequest(request)
            .setOldController(simpleDraweeView.getController())
            .build();
        simpleDraweeView.setController(controller);
    }
}
