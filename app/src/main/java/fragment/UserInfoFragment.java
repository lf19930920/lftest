package fragment;

import android.app.Dialog;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.xykgj.tx.xinyongkaguanjia.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.List;

import activity.CallUsActivity;
import activity.CreditApplyActivity;
import activity.LoginActivity;
import activity.RealNameAuthActivity;
import activity.SettingActivity;
import base.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import evenbusbean.EventBusLoginBean;
import utils.SpUtils;

/**
 * Created by mayn on 2018/6/19.
 * 个人中心fragment
 */

public class UserInfoFragment extends BaseFragment {
    public static final String TAG = UserInfoFragment.class.getSimpleName();
    @BindView(R.id.tv_userinfo_setting)
    TextView tvUserinfoSetting;
    @BindView(R.id.ll_userinfo_realname)
    LinearLayout llUserinfoRealname;
    @BindView(R.id.ll_userinfo_gainmoney)
    LinearLayout llUserinfoGainmoney;
    @BindView(R.id.ll_userinfo_applycard)
    LinearLayout llUserinfoApplycard;
    @BindView(R.id.ll_userinfo_helpcenter)
    LinearLayout llUserinfoHelpcenter;
    @BindView(R.id.ll_userinfo_callus)
    LinearLayout llUserinfoCallus;
    Unbinder mUnbinder;
    @BindView(R.id.iv_userinfo_icon)
    ImageView ivUserinfoIcon;
    @BindView(R.id.tv_userinfo_login)
    TextView tvUserinfoLogin;



    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_userinfo, null);
        mUnbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    protected void initData() {
        if (TextUtils.isEmpty(SpUtils.getString(mActivity,"userphone"))){
            tvUserinfoLogin.setText("登录/注册");
        }else {
            tvUserinfoLogin.setText(SpUtils.getString(mActivity,"userphone"));
        }
    }

    @OnClick({R.id.tv_userinfo_setting, R.id.tv_userinfo_login, R.id.ll_userinfo_realname,
            R.id.ll_userinfo_gainmoney, R.id.ll_userinfo_applycard, R.id.ll_userinfo_helpcenter,
            R.id.ll_userinfo_callus,R.id.iv_userinfo_icon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_userinfo_setting:
                //跳转到设置界面
                jumpToActivity(mActivity, SettingActivity.class);
                break;
            case R.id.tv_userinfo_login:
                if (TextUtils.isEmpty(SpUtils.getString(mActivity,"token"))){
                    //登录
                    Intent intent = new Intent(mActivity, LoginActivity.class);
                    startActivity(intent);
                }else {
                    return;
                }
                break;
            case R.id.iv_userinfo_icon:
                if (!TextUtils.isEmpty(SpUtils.getString(mActivity,"token"))){
                    //修改头像
                    //弹出修改头像diaglog
                    showEditIconDialog();
                }else {
                    //登录
                    Intent intent = new Intent(mActivity, LoginActivity.class);
                    startActivity(intent);
                }

                break;
            case R.id.ll_userinfo_realname:
                //先判断是否进行过实名认证，没有进行过实名认证，去认证，进行过跳转到已实名认证的界面
                //实名认证
                jumpToActivity(mActivity, RealNameAuthActivity.class);
                break;
            case R.id.ll_userinfo_gainmoney:
                //赚客
                break;
            case R.id.ll_userinfo_applycard:
                //办卡
                jumpToActivity(mActivity, CreditApplyActivity.class);
                break;
            case R.id.ll_userinfo_helpcenter:
                //帮助中心
                break;
            case R.id.ll_userinfo_callus:
                //联系我们
                jumpToActivity(mActivity, CallUsActivity.class);
                break;
        }
    }

    /**
     * 弹出修改头像diaglog
     */
    private void showEditIconDialog() {
        final Dialog dialog = new Dialog(mActivity,R.style.EditIconDialogStyle);
        View view = LayoutInflater.from(mActivity).inflate(R.layout.dialog_editicon,null,false);
        TextView takePhoto = (TextView) view.findViewById(R.id.takePhoto);
        TextView choosePhoto = (TextView) view.findViewById(R.id.choosePhoto);
        TextView cancle = (TextView) view.findViewById(R.id.cancle);
        //照像
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCameraPhoto();
                dialog.dismiss();
            }
        });
        //相册
        choosePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPhotoGallery();
                dialog.dismiss();
            }
        });
        //取消
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity( Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = -1;
        // 将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialog.show();//显示对话框
    }



    /**
     * 照相
     */
    private void getCameraPhoto() {
        PictureSelector.create(UserInfoFragment.this)
                .openCamera(PictureMimeType.ofImage())
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    /**
     * 相册
     */
    private void getPhotoGallery() {
        PictureSelector.create(UserInfoFragment.this)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(R.style.picture_default_style)//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                .maxSelectNum(1)// 最大图片选择数量 int
                .minSelectNum(0)// 最小选择数量 int
                .imageSpanCount(4)// 每行显示个数 int
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .isCamera(false)// 是否显示拍照按钮 true or false
                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
                .enableCrop(true)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or fals
                .withAspectRatio(16,9)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示 true or false
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .circleDimmedLayer(true)// 是否圆形裁剪 true or false
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                .openClickSound(true)// 是否开启点击声音 true or false
                .previewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                .cropCompressQuality(90)// 裁剪压缩质量 默认90 int
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .synOrAsy(false)//同步true或异步false 压缩 默认同步
                .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                .isDragFrame(true)// 是否可拖动裁剪框(固定)
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventBusLoginBean bean) {
        /* Do something */
        Log.i(TAG, "onEvent: ----------->");
        initData();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    Log.i(TAG, "onActivityResult: selectList.size()="+selectList.size());
                    Glide.with(mActivity).load(new File(selectList.get(0).getPath()))
                            .apply(RequestOptions.circleCropTransform()).into(ivUserinfoIcon);
                    if (selectList.get(0).isCompressed()){
                        Glide.with(mActivity).load(new File(selectList.get(0).getCompressPath()))
                                .apply(RequestOptions.circleCropTransform()).into(ivUserinfoIcon);
                    }
                    //包括裁剪和压缩后的缓存，要在上传成功后调用，注意：需要系统sd卡权限
                    PictureFileUtils.deleteCacheDirFile(mActivity);
                    break;
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
        EventBus.getDefault().unregister(this);
        //Test
    }
}
