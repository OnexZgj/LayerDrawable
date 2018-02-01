package com.it.onex.layerdrawable;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Linsa on 2018/2/1:10:49.
 * des:
 */

public class AudioRecoderDialog extends BasePopupWindow {

    private final ImageView imageView;
    private final TextView textView;

    public AudioRecoderDialog(Context context) {
        super(context);
        View contentView = LayoutInflater.from(context).inflate(R.layout.layout_recoder_dialog, null);
        imageView = (ImageView) contentView.findViewById(R.id.progress_mic);
        textView = (TextView) contentView.findViewById(R.id.text1);
        setContentView(contentView);
    }

    public void setLevel(int level) {
        Drawable drawable = imageView.getDrawable();
        drawable.setLevel(3000 + 6000 * level / 100);
    }

    public void setTime(long time) {
        textView.setText(ProgressTextUtils.getProgressText(time));
    }
}
