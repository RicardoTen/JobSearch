package com.boss.im.holder;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.boss.R;
import com.boss.company1.AAActivity;
import com.boss.db.User;
import com.boss.im.base.ImageLoaderFactory;
import com.boss.im.ui.UserInfoActivity;

import butterknife.BindView;


public class SearchUserHolder extends BaseViewHolder {

  @BindView(R.id.avatar)
  public ImageView avatar;
  @BindView(R.id.name)
  public TextView name;
  @BindView(R.id.btn_add)
  public Button btn_add;

  public SearchUserHolder(Context context, ViewGroup root, OnRecyclerViewListener onRecyclerViewListener) {
    super(context, root, R.layout.item_search_user,onRecyclerViewListener);
  }

  @Override
  public void bindData(Object o) {
    final User user =(User)o;
    ImageLoaderFactory.getLoader().loadAvator(avatar,user.getAvator(), R.drawable.head);
    name.setText(user.getNick());
    btn_add.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {//查看个人详情
          Bundle bundle = new Bundle();
          bundle.putSerializable("u", user);
          startActivity(UserInfoActivity.class,bundle);
        }
    });
  }
}