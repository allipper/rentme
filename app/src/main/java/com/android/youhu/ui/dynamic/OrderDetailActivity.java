package com.android.youhu.ui.dynamic;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.youhu.R;
import com.android.youhu.bean.RentMeResponse;
import com.android.youhu.ui.base.BaseActivity;
import com.android.youhu.ui.base.ParameterConstant;

import io.rong.imkit.RongIM;

public class OrderDetailActivity extends BaseActivity {
    private static final String TAG = OrderDetailActivity.class.getSimpleName();

    private TextView numberTextView;
    private TextView nameTextView;
    private TextView constellationTextView;
    private TextView locationTextView;
    private TextView dateTextView;
    private TextView timeTextView;
    private TextView durationTextView;
    private TextView telphoneTextView;
    private TextView cityTextView;
    private TextView addressTextView;
    private TextView costTextView;
    private TextView orderDateTextView;
    private TextView total_feeTextView;
    private RelativeLayout bottomRelativeLayout;
    private Button datingButton;

    private RentMeResponse.DataEntity.ItemsEntity data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        findViews();
        getData();
        setDataToView();
    }

    private void setDataToView() {
        numberTextView.setText(data.orderId);
        orderDateTextView.setText(data.createTime);
        nameTextView.setText(data.nickName);
        dateTextView.setText(data.meetTime);
        timeTextView.setText(data.meetTime);
        durationTextView.setText("2小时");
        telphoneTextView.setText(data.mobile);
        addressTextView.setText(data.meetAddress);
        costTextView.setText("￥" + data.totalPrice);
        total_feeTextView.setText("￥" + data.totalPrice);
    }

    private void getData() {
        data = getIntent().getExtras().getParcelable(ParameterConstant.PARAM_ITEM_DATA);
//        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
//
//            @Override
//            public UserInfo getUserInfo(String userId) {
//                Uri uri = null;
//                if (!TextUtils.isEmpty(data.)) {
//                    uri = Uri.parse(SharedPreUtils.getString(mContext, SharedPre.User
//                            .AVATARURL));
//                }
//
//                return new UserInfo(SharedPreUtils.getInt(mContext, SharedPre.User
//                        .USERID, 0) + "",
//                        SharedPreUtils.getString(mContext, SharedPre.User.NICKNAME),
//                        uri);//根据
//                // userId
//                // 去你的用户系统里查询对应的用户信息返回给融云 SDK。
//            }
//
//        }, true);
    }

    private void findViews() {
        backImageView = (TextView) findViewById(R.id.back);
        titleTextView = (TextView) findViewById(R.id.title);
        numberTextView = (TextView) findViewById(R.id.number);
        nameTextView = (TextView) findViewById(R.id.name);
        constellationTextView = (TextView) findViewById(R.id.constellation);
        locationTextView = (TextView) findViewById(R.id.location);
        dateTextView = (TextView) findViewById(R.id.date);
        timeTextView = (TextView) findViewById(R.id.time);
        durationTextView = (TextView) findViewById(R.id.duration);
        telphoneTextView = (TextView) findViewById(R.id.telphone);
        cityTextView = (TextView) findViewById(R.id.city);
        addressTextView = (TextView) findViewById(R.id.address);
        costTextView = (TextView) findViewById(R.id.cost);
        orderDateTextView = (TextView) findViewById(R.id.orderDate);
        total_feeTextView = (TextView) findViewById(R.id.total_fee);
        bottomRelativeLayout = (RelativeLayout) findViewById(R.id.bottomRl);
        datingButton = (Button) findViewById(R.id.dating);
        titleTextView.setText("订单详情");
        datingButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.dating: {
//                Intent it = new Intent(mContext, MinePayActivity.class);
//                it.putExtra("isCharged", false);
//                startActivity(it);
                /**
                 * 启动单聊界面。
                 *
                 * @param context      应用上下文。
                 * @param targetUserId 要与之聊天的用户 Id。
                 * @param title        聊天的标题，如果传入空值，则默认显示与之聊天的用户名称。
                 */
                RongIM.getInstance().startPrivateChat(mContext, data.userId + "", data.nickName);
                break;
            }
            default:
                super.onClick(view);
                break;
        }
    }
}

