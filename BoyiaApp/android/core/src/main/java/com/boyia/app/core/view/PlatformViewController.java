package com.boyia.app.core.view;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.view.Surface;
import android.view.View;

import com.boyia.app.core.BoyiaCoreJNI;
import com.boyia.app.core.texture.BoyiaTexture;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 同层渲染，原生view控制类
 */
public class PlatformViewController {
    private Context mContext;
    private PlatformPresentation mPresentation;
    private BoyiaTexture mTexture;

    public PlatformViewController(Context context) {
        mContext = context;
    }

    public void createPlatformView(PlatformViewCreationRequest request) {
        PlatformViewFactory factory = PlatformViewManager.getInstance()
                .getViewFactory(request.viewType);
        if (factory == null) {
            return;
        }

        VirtualDisplay display = createVirtualDisplay(mContext, request);
        mPresentation = new PlatformPresentation(
                mContext, request.viewId, factory, display.getDisplay());
        mPresentation.show();
    }

    // 创建VirtualDisplay
    private VirtualDisplay createVirtualDisplay(Context context, PlatformViewCreationRequest request) {
//        SurfaceTexture texture = new SurfaceTexture(request.textureId);
        mTexture = new BoyiaTexture(request.textureId);
        mTexture.getSurfaceTexture().setDefaultBufferSize(request.logicalWidth, request.logicalHeight);
        mTexture.setTextureUpdateNotifier(() -> {
            BoyiaCoreJNI.nativePlatformViewUpdate(request.viewId);
        });

        DisplayManager displayManager = (DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE);
        int densityDpi = context.getResources().getDisplayMetrics().densityDpi;
        return displayManager.createVirtualDisplay(
                "boyia-vd",
                request.logicalWidth,
                request.logicalHeight,
                densityDpi,
                mTexture.getSurface(),
                0);
    }

    public PlatformView getView() {
        return mPresentation.getPlatformView();
    }

    private int toPhysicalPixels(double logicalPixels) {
        float density = mContext.getResources().getDisplayMetrics().density;
        return (int) Math.round(logicalPixels * density);
    }

    public static class PlatformViewCreationRequest {
        public final String viewId;
        public final String viewType;
        public final int logicalWidth;
        public final int logicalHeight;
        public final int textureId;

        public PlatformViewCreationRequest(
                String viewId,
                String viewType,
                int logicalWidth,
                int logicalHeight,
                int textureId) {
            this.viewId = viewId;
            this.viewType = viewType;
            this.logicalWidth = logicalWidth;
            this.logicalHeight = logicalHeight;
            this.textureId = textureId;
        }
    }
}
