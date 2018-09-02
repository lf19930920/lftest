package activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.baidu.ocr.sdk.model.IDCardParams;
import com.baidu.ocr.sdk.model.IDCardResult;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.baidu.ocr.ui.camera.CameraNativeHelper;
import com.baidu.ocr.ui.camera.CameraView;
import com.xykgj.tx.xinyongkaguanjia.R;

import java.io.File;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import utils.MyFileUtils;

/**
 * Created by mayn on 2018/6/20.
 * 实名认证Activity
 */

public class RealNameAuthActivity extends BaseActivity {
    private static final int REQUEST_CODE_PICK_IMAGE_FRONT = 201;
    private static final int REQUEST_CODE_PICK_IMAGE_BACK = 202;
    private static final int REQUEST_CODE_CAMERA = 102;
    private String resultAccessToken;


    @BindView(R.id.ll_realnameauth_back)
    LinearLayout llRealnameauthBack;
    @BindView(R.id.tv_realname_complete)
    TextView tvRealnameComplete;
    @BindView(R.id.ib_realname_portrait)
    ImageButton ibRealnamePortrait;
    @BindView(R.id.ll_realname_portrait)
    LinearLayout llRealnamePortrait;
    @BindView(R.id.ib_realname_national)
    ImageButton ibRealnameNational;
    @BindView(R.id.ll_realname_national)
    LinearLayout llRealnameNational;
    @BindView(R.id.et_realname_name)
    EditText etRealnameName;
    @BindView(R.id.tv_realname_idnumber)
    TextView tvRealnameIdnumber;
    @BindView(R.id.btn_realname_commit)
    Button btnRealnameCommit;

    @Override
    public int initLayout() {
        return R.layout.activity_realnameauth;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        etRealnameName.setFocusable(false);

        etRealnameName.setFocusableInTouchMode(false);
        initAccessToken();
    }
    private void initAccessToken() {
        //初始化OCR
        OCR.getInstance(RealNameAuthActivity.this).initAccessToken(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken result) {
                // 调用成功，返回AccessToken对象
                String ocr_token = result.getAccessToken();
                Log.e("OCR", "ocr_token="+ ocr_token);
            }
            @Override
            public void onError(OCRError error) {
                // 调用失败，返回OCRError子类SDKError对象
                Log.e("OCR", "ocr初始化失败"+error.getErrorCode());
            }
        }, getApplicationContext());
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.ll_realnameauth_back, R.id.ib_realname_portrait, R.id.ib_realname_national, R.id.btn_realname_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_realnameauth_back:
                finish();
                break;
            case R.id.ib_realname_portrait:
                //身份证正面
                idCradZhengMian();
                break;
            case R.id.ib_realname_national:
                //身份证反面
                idCardFanMian();
                break;
            case R.id.btn_realname_commit:
                break;
        }
    }

    /**
     * 身份证正面
     */
    private void idCradZhengMian() {
        Intent intent = new Intent(RealNameAuthActivity.this, CameraActivity.class);
        intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                MyFileUtils.getSaveFile(getApplication()).getAbsolutePath());
        intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_FRONT);
        startActivityForResult(intent, REQUEST_CODE_CAMERA);
    }

    /**
     * 身份证反面
     */
    private void idCardFanMian() {
        Intent intent = new Intent(RealNameAuthActivity.this, CameraActivity.class);
        intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                MyFileUtils.getSaveFile(getApplication()).getAbsolutePath());
        intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_BACK);
        startActivityForResult(intent, REQUEST_CODE_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PICK_IMAGE_FRONT && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            String filePath = getRealPathFromURI(uri);
            recIDCard(IDCardParams.ID_CARD_SIDE_FRONT, filePath);
        }

        if (requestCode == REQUEST_CODE_PICK_IMAGE_BACK && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            String filePath = getRealPathFromURI(uri);
            recIDCard(IDCardParams.ID_CARD_SIDE_BACK, filePath);
        }

        if (requestCode == REQUEST_CODE_CAMERA && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                String contentType = data.getStringExtra(CameraActivity.KEY_CONTENT_TYPE);
                String filePath = MyFileUtils.getSaveFile(getApplicationContext()).getAbsolutePath();
                if (!TextUtils.isEmpty(contentType)) {
                    if (CameraActivity.CONTENT_TYPE_ID_CARD_FRONT.equals(contentType)) {
                        recIDCard(IDCardParams.ID_CARD_SIDE_FRONT, filePath);
                    } else if (CameraActivity.CONTENT_TYPE_ID_CARD_BACK.equals(contentType)) {
                        recIDCard(IDCardParams.ID_CARD_SIDE_BACK, filePath);
                    }
                }
            }
        }
    }
    private void recIDCard(String idCardSide, String filePath) {
        IDCardParams param = new IDCardParams();
        param.setImageFile(new File(filePath));
        // 设置身份证正反面
        param.setIdCardSide(idCardSide);
        // 设置方向检测
        param.setDetectDirection(true);
        // 设置图像参数压缩质量0-100, 越大图像质量越好但是请求时间越长。 不设置则默认值为20
        param.setImageQuality(10);

        OCR.getInstance(this).recognizeIDCard(param, new OnResultListener<IDCardResult>() {
            @Override
            public void onResult(IDCardResult result) {
                if (result != null) {
                    Log.e("执行顺序", "onResult="+result.getName().getWords()+"====="+result.getIdNumber().getWords());
                    tvRealnameIdnumber.setText(result.getIdNumber().getWords());
                    etRealnameName.setText(result.getName().getWords());
                    etRealnameName.setFocusableInTouchMode(true);
                    etRealnameName.setFocusable(true);
                    etRealnameName.requestFocus();
                }
            }

            @Override
            public void onError(OCRError error) {
                Log.e("执行顺序", "onError: --------》"+error.getErrorCode());
            }
        });
    }
    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    /**
     * 用明文ak，sk初始化
     */
    private void initAccessTokenWithAkSk() {
        Log.e("执行顺序", "initAccessTokenWithAkSk: ---->");
        OCR.getInstance(this).initAccessTokenWithAkSk(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken result) {
                resultAccessToken = result.getAccessToken();
                Log.e("执行顺序", "resultAccessToken----->"+resultAccessToken );
            }

            @Override
            public void onError(OCRError error) {
                error.printStackTrace();
            }
        }, getApplicationContext(),  "gMiysYewP57NfbPmEYZ1gSiT", "RpiwxStvWLygRxBiVva5FdE85wNlL3S0");
        CameraNativeHelper.init(RealNameAuthActivity.this, resultAccessToken, new CameraNativeHelper.CameraNativeInitCallback() {
            @Override
            public void onError(int errorCode, Throwable e) {
                String msg;
                switch (errorCode) {
                    case CameraView.NATIVE_SOLOAD_FAIL:
                        msg = "加载so失败，请确保apk中存在ui部分的so";
                        break;
                    case CameraView.NATIVE_AUTH_FAIL:
                        msg = "授权本地质量控制token获取失败";
                        break;
                    case CameraView.NATIVE_INIT_FAIL:
                        msg = "本地质量控制";
                        break;
                    default:
                        msg = String.valueOf(errorCode);
                }
                Log.e("执行顺序", "msg="+msg);
                Log.e("执行顺序", "onError: --------》"+errorCode);
            }
        });
    }


}
