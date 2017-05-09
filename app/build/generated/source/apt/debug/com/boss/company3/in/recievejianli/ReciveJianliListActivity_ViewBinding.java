// Generated code from Butter Knife. Do not modify!
package com.boss.company3.in.recievejianli;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import com.boss.R;
import com.boss.view.IconFontTextView;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class ReciveJianliListActivity_ViewBinding<T extends ReciveJianliListActivity> implements Unbinder {
  protected T target;

  public ReciveJianliListActivity_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.actionbarLeft = finder.findRequiredViewAsType(source, R.id.actionbar_left, "field 'actionbarLeft'", IconFontTextView.class);
    target.actionbarCenter = finder.findRequiredViewAsType(source, R.id.actionbar_center, "field 'actionbarCenter'", TextView.class);
    target.actionbarRight = finder.findRequiredViewAsType(source, R.id.actionbar_right, "field 'actionbarRight'", IconFontTextView.class);
    target.imgSerch = finder.findRequiredViewAsType(source, R.id.img_serch, "field 'imgSerch'", ImageView.class);
    target.tvDevider = finder.findRequiredViewAsType(source, R.id.tv_devider, "field 'tvDevider'", TextView.class);
    target.lvReviveJianli = finder.findRequiredViewAsType(source, R.id.lv_revive_jianli, "field 'lvReviveJianli'", ListView.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.actionbarLeft = null;
    target.actionbarCenter = null;
    target.actionbarRight = null;
    target.imgSerch = null;
    target.tvDevider = null;
    target.lvReviveJianli = null;

    this.target = null;
  }
}
