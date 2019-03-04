package hanivan.mokalemesgha.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

import hanivan.mokalemesgha.R;

public class Helper {

    private Context context;
    private AlertDialog.Builder builder;

    private static final String EMAIL = "mikaelrzn@gmail.com";
    private static final String TELEGRAM_ID = "ffdfdfgfgghghghgh";
    private static final String TELEGRAM_CHANNEL = "hghghssgfgddf55";
    private static final String DEVELOPER_ID_BAZAAR = "159753492132";
    private static final String PACKAGE_NAME = "com.danielkim.soundrecordersoft";

    public Helper(Context context) {

        this.context = context;
        builder = new AlertDialog.Builder(context);
    }

    public void rate_bazaar() {
        builder.setMessage("لطفا با امتیاز مناسب در کافه بازار از برنامه حمایت نمایید");
        builder.setPositiveButton(" ثبت امتیاز", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_EDIT);
                        intent.setData(Uri.parse("bazaar://details?id=" + PACKAGE_NAME));
                        intent.setPackage("com.farsitel.bazaar");
                        context.startActivity(intent);
                    }
                }
        );
        builder.setNegativeButton("انصراف", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }
        );
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void apps_bazaar() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("bazaar://collection?slug=by_author&aid=" + DEVELOPER_ID_BAZAAR));
        intent.setPackage("com.farsitel.bazaar");
        context.startActivity(intent);
    }

    public void share_bazaar() {
        builder.setMessage("می توانید این برنامه را برای دوستان خود ارسال کنید");
        builder.setPositiveButton(" ارسال برای دوستان", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_SUBJECT, context.getResources().getString(R.string.app_name));
                        intent.putExtra(Intent.EXTRA_TEXT, "https://com.danielkim.soundrecordersoftazaar.ir/app/" + PACKAGE_NAME + "/?l=fa");
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(Intent.createChooser(intent, "یک گزینه را انتخاب نمایید "));
                    }
                }
        );
        builder.setNegativeButton("انصراف", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }
        );
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void support_email() {

        builder.setMessage("لطفا نظرات ، پیشنهادات و انتقادات خود را برای ما ارسال کنید");
        builder.setPositiveButton("ارسال ایمیل", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("message/rfc822");
                        i.putExtra(Intent.EXTRA_EMAIL, new String[]{EMAIL});
                        i.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name));
                        i.putExtra(Intent.EXTRA_TEXT, "متن : ");
                        try {
                            context.startActivity(Intent.createChooser(i, "Send mail..."));
                        } catch (android.content.ActivityNotFoundException ex) {
                        }
                    }
                }
        );
        builder.setNegativeButton("انصراف", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }
        );
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void support_telegram() {

        builder.setMessage("لطفا نظرات ، پیشنهادات و انتقادات خود را برای ما ارسال کنید");
        builder.setPositiveButton("اتصال به تلگرام ما", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/" + TELEGRAM_ID)));

                    }
                }
        );
        builder.setNegativeButton("انصراف", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }
        );
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void learn() {

        builder.setMessage("آموزش کامل کار با برنامه را می توانید در کانال برنامه مشاهده نمایید");
        builder.setPositiveButton("اتصال به کانال برنامه", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/" + TELEGRAM_CHANNEL)));
                    }
                }
        );
        builder.setNegativeButton("انصراف", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }
        );
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /////////////////////

}
