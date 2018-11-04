package util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PictureUtil {

    /**
     * 获取网络图片
     * @param pictureUrl 图片网络地址
     * @return Bitmap 返回位图
     */
    public Bitmap getPicture(String pictureUrl){
        URL url;
        HttpURLConnection connection=null;
        Bitmap bitmap=null;
        try {
            url = new URL(pictureUrl);
            connection=(HttpURLConnection)url.openConnection();
            connection.setConnectTimeout(6000); //超时设置
            connection.setDoInput(true);
            connection.setUseCaches(false); //设置不使用缓存
            InputStream inputStream=connection.getInputStream();
            bitmap= BitmapFactory.decodeStream(inputStream);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 保存位图到本地
     * @param bitmap
     * @param name 图片名
     * @return void
     */
    public void savaImage(Bitmap bitmap, String name){
        String path= Environment.getExternalStorageDirectory()+"/wechat/img";
        File file=new File(path);
        FileOutputStream fileOutputStream=null;
        //文件夹不存在，则创建它
        if(!file.exists()){
            file.mkdir();
        }

        try {
            fileOutputStream=new FileOutputStream(path+"/"+name+".png");
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100,fileOutputStream);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 展示本地图片到ImageView
     * @param imageView
     * @param name 图片名
     * @return void
     */
    public void setImage(ImageView imageView, String name){
        String path= Environment.getExternalStorageDirectory()+"/wechat/img";
        imageView.setImageURI(Uri.fromFile(new File(path+"/"+name+".png")));

    }

}
