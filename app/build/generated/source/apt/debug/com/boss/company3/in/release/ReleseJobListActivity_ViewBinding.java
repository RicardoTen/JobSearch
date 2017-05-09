// Generated code from Butter Knife. Do not modify!
package com.boss.company3.in.release;

import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import com.boss.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class ReleseJobListActivity_ViewBinding<T extends ReleseJobListActivity> implements Unbinder {
  protected T target;

  public ReleseJobListActivity_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.lvJob = finder.findRequiredViewAsType(source, R.id.lv_job, "field 'lvJob'", ListView.class);
    target.includeShujujiazaiTxt = finder.findRequiredViewAsType(source, R.id.include_shujujiazai_txt, "field 'includeShujujiazaiTxt'", TextView.class);
    target.includeShujujiazai = finder.findRequiredViewAsType(source, R.id.include_shujujiazai, "field 'includeShujujiazai'", LinearLayout.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.lvJob = null;
    target.includeShujujiazaiTxt = null;
    target.includeShujujiazai = null;

    this.target = null;
  }
}
