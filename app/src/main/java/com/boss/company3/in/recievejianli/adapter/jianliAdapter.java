package com.boss.company3.in.recievejianli.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.boss.BossApplication;
import com.boss.R;
import com.boss.company1.UserAdapter;
import com.boss.db.RecieveJianLi;
import com.boss.db.User;
import com.boss.util.BossConstants;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.List;

/**
 * Created by 滕新科 on 2017/4/26.
 */

public class jianliAdapter extends BaseAdapter {
    public int getCount() {
        return listdata.size();
    }

    private Context context;
    private LayoutInflater inflater;
    UserAdapter adap;
    List<RecieveJianLi> listdata;
    private RecieveJianLi jianli;

    public jianliAdapter(Context context, List<RecieveJianLi> hfListItems) {
        this.context = context;
        listdata = hfListItems;
        this.inflater = LayoutInflater.from(context);
    }

    public Object getItem(int position) {
        return listdata.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final jianliAdapter.ViewHolder holder;
        if (convertView == null) {
            // 实例化ViewHolder
            holder = new jianliAdapter.ViewHolder();
            // 获得listview的item条目xml布局文件oww
            convertView = inflater.inflate(
                    R.layout.item_company1_companyfragment1, parent, false);
            holder.mNick = (TextView) convertView
                    .findViewById(R.id.item_company1_companyfragment1_nick);
            holder.mAvatar = (ImageView) convertView
                    .findViewById(R.id.item_company1_companyfragment1_avatar);
            holder.mCompanyName = (TextView) convertView.findViewById(R.id.item_company1_companyfragment1_company);
            holder.mMajor = (TextView) convertView.findViewById(R.id.item_company1_companyfragment1_state);

            // 最后setTag,也就是类似保存convertView，也就是保存了item
            convertView.setTag(holder);

        } else {
            // 如果convertView前面保存过，这里就不为空，那么我们把item拿过来用
            holder = (jianliAdapter.ViewHolder) convertView.getTag();
        }
        jianli = listdata.get(position);
        if (jianli.getPostJianliUserNme() != null) {
            holder.mNick.setText(jianli.getPostJianliUserNme());
        }
        // 判断
      // 否则先判断头像地址是否为空，为空则用默认头像，否则使用地址头像
            String imgUrl = jianli.getPostJianliUserAv();
            if (imgUrl == null || imgUrl.isEmpty()) {
                holder.mAvatar.setImageResource(R.drawable.ic_launcher);
            } else {

                ImageLoader.getInstance().displayImage(
                        imgUrl,
                        holder.mAvatar,
                        BossApplication.getInstance().getOptions(
                                R.drawable.ic_launcher),
                        new SimpleImageLoadingListener() {

                            @Override
                            public void onLoadingComplete(String imageUri,
                                                          View view, Bitmap loadedImage) {
                                super.onLoadingComplete(imageUri, view,
                                        loadedImage);
                                holder.mAvatar.setImageBitmap(loadedImage);
                            }
                        });
            }


        if (jianli.getRecieveCompanyName() != null) {
            holder.mCompanyName.setText(jianli.getRecieveCompanyName());
        }
        if (jianli.getRecieveCompanyJob() != null) {
            holder.mMajor.setText(jianli.getRecieveCompanyJob());
        }

        return convertView;
    }

    class ViewHolder {
        /**
         * 昵称
         */
        TextView mNick;
        /**
         * 头像
         */
        ImageView mAvatar;
        /**
         * 公司名字
         */
        TextView mCompanyName;
        /**
         * 职位
         */
        TextView mMajor;

    }
}
