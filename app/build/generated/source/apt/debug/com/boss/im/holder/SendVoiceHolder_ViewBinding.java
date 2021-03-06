// Generated code from Butter Knife. Do not modify!
package com.boss.im.holder;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import com.boss.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class SendVoiceHolder_ViewBinding<T extends SendVoiceHolder> implements Unbinder {
  protected T target;

  public SendVoiceHolder_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.iv_avatar = finder.findRequiredViewAsType(source, R.id.iv_avatar, "field 'iv_avatar'", ImageView.class);
    target.iv_fail_resend = finder.findRequiredViewAsType(source, R.id.iv_fail_resend, "field 'iv_fail_resend'", ImageView.class);
    target.tv_time = finder.findRequiredViewAsType(source, R.id.tv_time, "field 'tv_time'", TextView.class);
    target.tv_voice_length = finder.findRequiredViewAsType(source, R.id.tv_voice_length, "field 'tv_voice_length'", TextView.class);
    target.iv_voice = finder.findRequiredViewAsType(source, R.id.iv_voice, "field 'iv_voice'", ImageView.class);
    target.tv_send_status = finder.findRequiredViewAsType(source, R.id.tv_send_status, "field 'tv_send_status'", TextView.class);
    target.progress_load = finder.findRequiredViewAsType(source, R.id.progress_load, "field 'progress_load'", ProgressBar.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.iv_avatar = null;
    target.iv_fail_resend = null;
    target.tv_time = null;
    target.tv_voice_length = null;
    target.iv_voice = null;
    target.tv_send_status = null;
    target.progress_load = null;

    this.target = null;
  }
}
