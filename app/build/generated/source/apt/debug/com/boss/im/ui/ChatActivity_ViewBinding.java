// Generated code from Butter Knife. Do not modify!
package com.boss.im.ui;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import com.boss.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class ChatActivity_ViewBinding<T extends ChatActivity> implements Unbinder {
  protected T target;

  private View view2131558878;

  private View view2131558876;

  private View view2131558877;

  private View view2131558880;

  private View view2131558881;

  private View view2131558882;

  private View view2131558873;

  private View view2131558874;

  private View view2131558875;

  public ChatActivity_ViewBinding(final T target, Finder finder, Object source) {
    this.target = target;

    View view;
    target.ll_chat = finder.findRequiredViewAsType(source, R.id.ll_chat, "field 'll_chat'", LinearLayout.class);
    target.sw_refresh = finder.findRequiredViewAsType(source, R.id.sw_refresh, "field 'sw_refresh'", SwipeRefreshLayout.class);
    target.rc_view = finder.findRequiredViewAsType(source, R.id.rc_view, "field 'rc_view'", RecyclerView.class);
    view = finder.findRequiredView(source, R.id.edit_msg, "field 'edit_msg' and method 'onEditClick'");
    target.edit_msg = finder.castView(view, R.id.edit_msg, "field 'edit_msg'", EditText.class);
    view2131558878 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onEditClick(p0);
      }
    });
    view = finder.findRequiredView(source, R.id.btn_chat_add, "field 'btn_chat_add' and method 'onAddClick'");
    target.btn_chat_add = finder.castView(view, R.id.btn_chat_add, "field 'btn_chat_add'", Button.class);
    view2131558876 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onAddClick(p0);
      }
    });
    view = finder.findRequiredView(source, R.id.btn_chat_emo, "field 'btn_chat_emo' and method 'onEmoClick'");
    target.btn_chat_emo = finder.castView(view, R.id.btn_chat_emo, "field 'btn_chat_emo'", Button.class);
    view2131558877 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onEmoClick(p0);
      }
    });
    target.btn_speak = finder.findRequiredViewAsType(source, R.id.btn_speak, "field 'btn_speak'", Button.class);
    view = finder.findRequiredView(source, R.id.btn_chat_voice, "field 'btn_chat_voice' and method 'onVoiceClick'");
    target.btn_chat_voice = finder.castView(view, R.id.btn_chat_voice, "field 'btn_chat_voice'", Button.class);
    view2131558880 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onVoiceClick(p0);
      }
    });
    view = finder.findRequiredView(source, R.id.btn_chat_keyboard, "field 'btn_chat_keyboard' and method 'onKeyClick'");
    target.btn_chat_keyboard = finder.castView(view, R.id.btn_chat_keyboard, "field 'btn_chat_keyboard'", Button.class);
    view2131558881 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onKeyClick(p0);
      }
    });
    view = finder.findRequiredView(source, R.id.btn_chat_send, "field 'btn_chat_send' and method 'onSendClick'");
    target.btn_chat_send = finder.castView(view, R.id.btn_chat_send, "field 'btn_chat_send'", Button.class);
    view2131558882 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSendClick(p0);
      }
    });
    target.layout_more = finder.findRequiredViewAsType(source, R.id.layout_more, "field 'layout_more'", LinearLayout.class);
    target.layout_add = finder.findRequiredViewAsType(source, R.id.layout_add, "field 'layout_add'", LinearLayout.class);
    target.layout_emo = finder.findRequiredViewAsType(source, R.id.layout_emo, "field 'layout_emo'", LinearLayout.class);
    target.layout_record = finder.findRequiredViewAsType(source, R.id.layout_record, "field 'layout_record'", RelativeLayout.class);
    target.tv_voice_tips = finder.findRequiredViewAsType(source, R.id.tv_voice_tips, "field 'tv_voice_tips'", TextView.class);
    target.iv_record = finder.findRequiredViewAsType(source, R.id.iv_record, "field 'iv_record'", ImageView.class);
    view = finder.findRequiredView(source, R.id.tv_picture, "method 'onPictureClick'");
    view2131558873 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onPictureClick(p0);
      }
    });
    view = finder.findRequiredView(source, R.id.tv_camera, "method 'onCameraClick'");
    view2131558874 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCameraClick(p0);
      }
    });
    view = finder.findRequiredView(source, R.id.tv_location, "method 'onLocationClick'");
    view2131558875 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onLocationClick(p0);
      }
    });
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ll_chat = null;
    target.sw_refresh = null;
    target.rc_view = null;
    target.edit_msg = null;
    target.btn_chat_add = null;
    target.btn_chat_emo = null;
    target.btn_speak = null;
    target.btn_chat_voice = null;
    target.btn_chat_keyboard = null;
    target.btn_chat_send = null;
    target.layout_more = null;
    target.layout_add = null;
    target.layout_emo = null;
    target.layout_record = null;
    target.tv_voice_tips = null;
    target.iv_record = null;

    view2131558878.setOnClickListener(null);
    view2131558878 = null;
    view2131558876.setOnClickListener(null);
    view2131558876 = null;
    view2131558877.setOnClickListener(null);
    view2131558877 = null;
    view2131558880.setOnClickListener(null);
    view2131558880 = null;
    view2131558881.setOnClickListener(null);
    view2131558881 = null;
    view2131558882.setOnClickListener(null);
    view2131558882 = null;
    view2131558873.setOnClickListener(null);
    view2131558873 = null;
    view2131558874.setOnClickListener(null);
    view2131558874 = null;
    view2131558875.setOnClickListener(null);
    view2131558875 = null;

    this.target = null;
  }
}
