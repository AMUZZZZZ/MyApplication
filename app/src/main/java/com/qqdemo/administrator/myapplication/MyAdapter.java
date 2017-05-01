package com.qqdemo.administrator.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.Formatter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;

import java.io.File;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

import static com.qqdemo.administrator.myapplication.MyApplication.IpAddress;
import static com.qqdemo.administrator.myapplication.R.id.tvProgress;

/**
 * Created by Administrator on 2017/5/1.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private static final String TAG = "2222222";
    List<FormData.FormDataBean> list;
    Context mContext;
    public MyAdapter( Context context, List<FormData.FormDataBean> points) {
        mContext=context;
        this.list=points;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_item, parent, false);
       ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final FormData.FormDataBean formDataBean = list.get(position);
        String fname = formDataBean.getFname();
        int fsize = formDataBean.getFsize();
        String fdate = formDataBean.getFdate();
        holder.fname.setText(fname.substring(fname.indexOf('_')+1));
        holder.fsize.setText("大小："+getPrintSize(fsize));
        holder.fdate.setText(fdate);
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: ");
                OkGo.get("http://" + IpAddress + ":8080/Login/DownLoadServlet")
                        .params("url",formDataBean.getFurl())
                        .execute(new FileCallback() {
                            @Override
                            public void onSuccess(File file, Call call, Response response) {
                                Toast.makeText(mContext, "下载完成", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(Call call, Response response, Exception e) {
                                Log.i(TAG, "onError: ");
                                Toast.makeText(mContext, "下载失败", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {

                                String downloadLength = Formatter.formatFileSize(mContext, currentSize);
                                String totalLength = Formatter.formatFileSize(mContext, totalSize);
                                holder.mDownloadSize.setText(downloadLength + "/" + totalLength);
                                String netSpeed = Formatter.formatFileSize(mContext, networkSpeed);
                                holder.mNetSpeed.setText(netSpeed + "/S");
                                holder.mTvProgress.setText((Math.round(progress * 10000) * 1.0f / 100) + "%");
                                Log.i(TAG, "downloadProgress: "+(int)progress*(-1)/1024);

                            }
                        });

            }
        });

    }

    private void download(String furl,final ProgressBar progressBar) {

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView fname;
        TextView fsize;
        TextView fdate;
        Button btn;


        TextView mDownloadSize;

        TextView mNetSpeed;

        TextView mTvProgress;

        public ViewHolder(View itemView) {
            super(itemView);
            fname = (TextView) itemView.findViewById(R.id.fname);
            fsize = (TextView) itemView.findViewById(R.id.fsize);
            fdate = (TextView) itemView.findViewById(R.id.fdate);
            btn = (Button) itemView.findViewById(R.id.btn_download);
            mDownloadSize = (TextView) itemView.findViewById(R.id.downloadSize);
            mNetSpeed = (TextView) itemView.findViewById(R.id.netSpeed);
            mTvProgress = (TextView) itemView.findViewById(tvProgress);
        }
    }
    public static String getPrintSize(long size) {
        //如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        if (size < 1024) {
            return String.valueOf(size) + "B";
        } else {
            size = size / 1024;
        }
        //如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        //因为还没有到达要使用另一个单位的时候
        //接下去以此类推
        if (size < 1024) {
            return String.valueOf(size) + "KB";
        } else {
            size = size / 1024;
        }
        if (size < 1024) {
            //因为如果以MB为单位的话，要保留最后1位小数，
            //因此，把此数乘以100之后再取余
            size = size * 100;
            return String.valueOf((size / 100)) + "."
                    + String.valueOf((size % 100)) + "MB";
        } else {
            //否则如果要以GB为单位的，先除于1024再作同样的处理
            size = size * 100 / 1024;
            return String.valueOf((size / 100)) + "."
                    + String.valueOf((size % 100)) + "GB";
        }
    }

}
