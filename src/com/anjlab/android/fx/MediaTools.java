package com.anjlab.android.fx;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

public class MediaTools extends BaseTools {
	
	private static boolean isMuted;
	
	public static void playSound(Context ctx, int soundId){
		if (isMuted)
			return;
    	MediaPlayer mp = MediaPlayer.create(ctx, soundId);           
        mp.setOnCompletionListener(new OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
        mp.start();
    }
	
	public static boolean isMuted(){
		return isMuted;
	}
	
	public static void setMuted(boolean value){
		isMuted = value;
	}
}
