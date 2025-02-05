package com.android.youhu.ui.mine;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.youhu.R;
import com.android.youhu.adapter.MineRentAdapter;
import com.android.youhu.bean.RentMeResponse;
import com.android.youhu.common.util.LoadDialogUtil;
import com.android.youhu.common.util.ToastUtils;
import com.android.youhu.common.util.Utils;
import com.android.youhu.net.HttpLoad;
import com.android.youhu.net.ResponseCallback;
import com.android.youhu.ui.base.ParameterConstant;
import com.android.youhu.ui.base.SwipeRefreshBaseActivity;
import com.android.youhu.ui.dynamic.OrderDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class MineRentActivity extends SwipeRefreshBaseActivity {
    private static final String TAG = MineRentActivity.class.getSimpleName();

    private List<RentMeResponse.DataEntity.ItemsEntity> datas = new ArrayList<>();
    private MineRentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_rent);
        findViews();
        setSwipeLayout();
        getDatas(true);
    }


    public void getDatas(boolean isShowDialog) {
        final Dialog dialog = LoadDialogUtil.createLoadingDialog(mContext, R.string.loading);
        if (isShowDialog) {
            dialog.show();
        }
        HttpLoad.Order.getMineRent(TAG, Utils.getToken(mContext), pagination.currentPage + "",
                pagination.pageSize + "", new ResponseCallback<RentMeResponse>(mContext) {


                    @Override
                    public void onRequestSuccess(RentMeResponse result) {
                        dialog.dismiss();
                        swipeLayout.setRefreshing(false);
                        if (isRefresh) {
                            isRefresh = false;
                            datas = result.data.items;
                            pagination = result.data.pager;
                        } else {
                            datas.addAll(result.data.items);
                        }

                        if (adapter == null) {
                            adapter = new MineRentAdapter(mContext, datas);
                            listView.setAdapter(adapter);
                        } else {
                            adapter.setData(datas);
                        }
                    }

                    @Override
                    public void onReuquestFailed(String error) {
                        dialog.dismiss();
                        swipeLayout.setRefreshing(false);
                        ToastUtils.show(mContext, error);
                    }
                });
    }

    private void findViews() {
        backImageView = (TextView) findViewById(R.id.back);
        titleTextView = (TextView) findViewById(R.id.title);
        titleTextView.setText("我租到的");
        listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(mContext, OrderDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(ParameterConstant.PARAM_ITEM_DATA, datas.get(position));
                it.putExtras(bundle);
                startActivity(it);
            }
        });
    }

}

