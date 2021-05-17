package com.example.rid_project.ui.activity;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.text.TextUtils;

public class MediaScannerActivity {
    private Context mContext;
    private static volatile MediaScannerActivity mMediaInstance = null;
    private MediaScannerConnection mMediaScanner;
    //private MediaScannerConnection.MediaScannerConnectionClient mMediaScannerClient;

    private String mFilePath;

    public static MediaScannerActivity getInstance( Context context ) {
        if( null == context )
            return null;

        if( null == mMediaInstance )
            mMediaInstance = new MediaScannerActivity( context );
        return mMediaInstance;
    }

    public static void releaseInstance() {
        if ( null != mMediaInstance ) {
            mMediaInstance = null;
        }
    }


    private MediaScannerActivity(Context context) {
        mContext = context;

        mFilePath = "";

        MediaScannerConnection.MediaScannerConnectionClient mediaScanClient;
        mediaScanClient = new MediaScannerConnection.MediaScannerConnectionClient(){
            @Override public void onMediaScannerConnected() {
                mMediaScanner.scanFile(mFilePath, null);
//                mFilePath = path;
            }

            @Override public void onScanCompleted(String path, Uri uri) {
                System.out.println("::::MediaScan Success::::");

                mMediaScanner.disconnect();
            }
        };
        mMediaScanner = new MediaScannerConnection(mContext, mediaScanClient);
    }

    public void mediaScanning(final String path) {

        if( TextUtils.isEmpty(path) )
            return;
        mFilePath = path;

        if( !mMediaScanner.isConnected() )
            mMediaScanner.connect();

        //mMediaScanner.scanFile( path,null );
    }
}
