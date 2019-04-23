package com.wou.commonutils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wou.commonutils.view.CustomDialog;
import com.wou.commonutils.view.CustomDialogTran;

public class ViewTools {

    private static String OK = "确定";
    private static String CANCEL = "取消";
    private static String PROMPT = "提示信息";

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    /**
     * 显示带标题的alert提示信息
     *
     * @param context
     * @param title
     * @param message
     */
    public static void showMessage(Context context, String title, String message) {
        AlertDialog.Builder builders = new AlertDialog.Builder(context);
        if (title != null) {
            builders.setTitle(title);
        } else {
            builders.setTitle(PROMPT);
        }
        builders.setMessage(message);
        builders.setPositiveButton(OK, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builders.show();
    }

    /**
     * 全局dialog显示
     *
     * @param context
     * @param title
     * @param message
     */
    public static void showSystemMessage(Context context, String title, String message, DialogInterface.OnClickListener listener) {
        CustomDialog.Builder builders = new CustomDialog.Builder(context);
        if (title != null) {
            builders.setTitle(title);
        } else {
            builders.setTitle(PROMPT);
        }
        builders.setMessage(message);
        builders.setPositiveButton(OK, listener);
        CustomDialog alert = builders.create();
        alert.setCancelable(false);
        alert.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alert.show();
    }

    public static void showAppSystemMessage(Context context, String title, String message, String okText, String cancelText, DialogInterface.OnClickListener okListener,
                                            DialogInterface.OnClickListener cancelListener) {
        CustomDialog.Builder builders = new CustomDialog.Builder(context);
        if (title != null) {
            builders.setTitle(title);
        }
        builders.setMessage(message);
        builders.setPositiveButton(okText, okListener);
        builders.setNegativeButton(cancelText, cancelListener);
//            builders.create().show();
        CustomDialog alert = builders.create();
        alert.setCancelable(true);
//            alert.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alert.show();
    }

//    public static boolean validateDataIsNull(Context aContext, EditText editText, String name) {
//        if (UtilTools.isNullOrEmpty(editText.getText().toString().trim())) {
//            ViewTools.showShortToast(aContext, "您还没有输入" + name);
//            editText.requestFocus();
//            return true;
//        }
//        return false;
//    }

    /**
     * 显示带标题的选择提示信息
     *
     * @param context
     * @param title
     * @param items
     * @param listener
     */
    public static void showSelect(Context context, String title, String[] items, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builders = new AlertDialog.Builder(context);
        if (title != null) {
            builders.setTitle(title);
        } else {
            builders.setTitle(PROMPT);
        }
        builders.setItems(items, listener);
        builders.show();
    }

    /**
     * 显示不带标题的alert提示信息
     *
     * @param context
     * @param message
     */
    public static void showMessage(Context context, String message) {
        AlertDialog.Builder builders = new AlertDialog.Builder(context);
        builders.setTitle(PROMPT);
        builders.setMessage(message);
        builders.setPositiveButton(OK, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builders.show();
    }

    /**
     * 显示带标题，确认按钮的alert提示信息
     *
     * @param context
     * @param title
     * @param message
     * @param btnText
     * @param listener
     */
    public static void showMessage(Context context, String title, String message, String btnText, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builders = new AlertDialog.Builder(context);
        if (title != null) {
            builders.setTitle(title);
        } else {
            builders.setTitle(PROMPT);
        }
        builders.setMessage(message);
        builders.setPositiveButton(btnText, listener);
        builders.show();
    }


    /**
     * 显示带标题，确认，取消按钮的alert提示信息
     *
     * @param context
     * @param title
     * @param message
     * @param okText
     * @param cancelText
     * @param okListener
     * @param cancelListener
     */
    public static void showConfirm(Context context, String title, String message, String okText, String cancelText, DialogInterface.OnClickListener okListener,
                                   DialogInterface.OnClickListener cancelListener) {

        CustomDialog.Builder builder = new CustomDialog.Builder(context);
        if (title != null) {
            builder.setTitle(title);
        } else {
            builder.setTitle(PROMPT);
        }
        if (cancelText == null) {
            cancelText = CANCEL;
        }
        builder.setMessage(message);
        builder.setPositiveButton(okText, okListener);
        builder.setNegativeButton(cancelText, cancelListener);
        builder.create().show();
    }


    public static CustomDialog showConfirmGame(Context context, String title, String message, String okText, String cancelText, DialogInterface.OnClickListener okListener,
                                               DialogInterface.OnClickListener cancelListener) {

        CustomDialog.Builder builder = new CustomDialog.Builder(context);
        if (title != null) {
            builder.setTitle(title);
        } else {
            builder.setTitle(PROMPT);
        }
        if (cancelText == null) {
            cancelText = CANCEL;
        }
        builder.setMessage(message);
        builder.setPositiveButton(okText, okListener);
        builder.setNegativeButton(cancelText, cancelListener);
        CustomDialog dialog = builder.create();
        dialog.show();
        return dialog;
    }



    public static CustomDialogTran showTranConfirmGame(Context context, String title, String message, String okText, String cancelText, DialogInterface.OnClickListener okListener,
                                               DialogInterface.OnClickListener cancelListener) {

        CustomDialogTran.Builder builder = new CustomDialogTran.Builder(context);
        if (title != null) {
            builder.setTitle(title);
        } else {
            builder.setTitle(PROMPT);
        }
        if (cancelText == null) {
            cancelText = CANCEL;
        }
        builder.setMessage(message);
        builder.setPositiveButton(okText, okListener);
        builder.setNegativeButton(cancelText, cancelListener);
        CustomDialogTran dialog = builder.create();
        dialog.show();
        return dialog;
    }





    /**
     * 显示带标题，确认，的alert提示信息
     *
     * @param context
     * @param title
     * @param message
     * @param okText
     * @param okListener
     */
    public static void showConfirm(Context context, String title, String message, String okText, DialogInterface.OnClickListener okListener
    ) {

        CustomDialog.Builder builder = new CustomDialog.Builder(context);
        if (title != null) {
            builder.setTitle(title);
        } else {
            builder.setTitle(PROMPT);
        }
        builder.setMessage(message);
        builder.setPositiveButton(okText, okListener);
        builder.create().show();
    }

    /**
     * 显示带标题和三个按钮的alert提示信息
     *
     * @param context
     * @param title
     */
    public static void showConfirm(Context context, String title, String message, String okText, String neutralText, String cancelText,
                                   DialogInterface.OnClickListener okListener, DialogInterface.OnClickListener neutralListener, DialogInterface.OnClickListener cancelListener) {
        AlertDialog.Builder builders = new AlertDialog.Builder(context);
        if (title != null) {
            builders.setTitle(title);
        } else {
            builders.setTitle(PROMPT);
        }
        if (cancelText == null) {
            cancelText = CANCEL;
        }
        builders.setMessage(message);
        builders.setPositiveButton(okText, okListener);
        builders.setNeutralButton(neutralText, neutralListener);
        builders.setNegativeButton(cancelText, cancelListener);
        builders.show();
    }

    /**
     * 显示带标题，确认按钮的alert提示信息
     *
     * @param context
     * @param title
     * @param message
     */
    public static void showMessage(Context context, String title, String message, String okText, DialogInterface.OnClickListener okListener,
                                   DialogInterface.OnKeyListener onKeyListener) {
        AlertDialog.Builder builders = new AlertDialog.Builder(context);
        if (title != null) {
            builders.setTitle(title);
        } else {
            builders.setTitle(PROMPT);
        }
        builders.setMessage(message);
        builders.setPositiveButton(okText, okListener);
        builders.setOnKeyListener(onKeyListener);
        builders.show();
    }

    /**
     * 获取一个分割线
     */
    public static TextView getDivider(Context view, int height) {
        TextView tvDivider = new TextView(view);
        ViewGroup.LayoutParams paramsDivider = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
        tvDivider.setLayoutParams(paramsDivider);
        tvDivider.setBackgroundResource(android.R.drawable.divider_horizontal_dark);
        return tvDivider;
    }

    /**
     * 显示短时间的toast
     *
     * @param context
     * @param message
     */
    public static void showShortToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示长时间的toast
     *
     * @param context
     * @param message
     */
    public static void showLongToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 显示正在加载提示信息
     *
     * @param context
     * @param message
     */
    public static ProgressDialog showLoading(Context context, String title, String message) {
        return ProgressDialog.show(context, title, message, true, true);
    }

    /**
     * 显示正在加载提示信息
     *
     * @param context
     * @param message
     */
    public static ProgressDialog showLoading(Context context, String message) {
        return ProgressDialog.show(context, null, message, true, true);
    }


    /**
     * 隐藏弹出的对话框
     */
    public static void hideAlertDialog(AlertDialog alertDialog) {
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
            alertDialog = null;
        }
    }

    public static int setMakeMeasureSpec() {
        return View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
    }


    public static Dialog showHintDialog(Context context, String msg, boolean cancelable) {
        Dialog dialog = createLoadingDialog(context, msg, cancelable);
        dialog.show();
        return dialog;
    }

    public static Dialog showHintDialog(Context context, String msg) {
        Dialog dialog = createLoadingDialog(context, msg, false);
        dialog.show();
        return dialog;
    }

    public static Dialog showHintDialog(Context context, int stringid) {
        Dialog dialog = createLoadingDialog(context, context.getResources().getString(stringid), false);
        dialog.show();
        return dialog;
    }

    public static Dialog showHintDialog(Context context, int stringid, boolean cancelable) {
        Dialog dialog = createLoadingDialog(context, context.getResources().getString(stringid), cancelable);
        dialog.show();
        return dialog;
    }

    public static Dialog createLoadingDialog(Context context, String msg, boolean cancelable) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.loading_dialog, null);
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);
        ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
        // 加载动画
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(context, R.anim.loading_animation);
        // 使用ImageView显示动画
        spaceshipImage.startAnimation(hyperspaceJumpAnimation);
        tipTextView.setText(msg);
        Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog
        loadingDialog.setCancelable(cancelable);// 不可以用“返回键”取消
//        loadingDialog.setCanceledOnTouchOutside(true);
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));// 设置布局
        return loadingDialog;

    }


    /**
     * 显示正在加载提示信息
     *
     * @param context
     * @param message
     */
    public static ProgressDialog showLoading(Context context, String title, String message, boolean cancelable) {
        return ProgressDialog.show(context, title, message, true, cancelable);
    }


}
