package text.bawei.com.myglide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import static android.R.attr.resource;

public class MainActivity extends AppCompatActivity {
    //    http://static.cfanz.cn/uploads/gif/2015/08/06/20/cd68GR70HL.gif
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) findViewById(R.id.id_img);
        Glide.with(this)
                .load("http://static.cfanz.cn/uploads/gif/2015/08/06/20/cd68GR70HL.gif")
                .asGif()
                .centerCrop()
                .error(R.mipmap.ic_launcher)
                //1.placeholder() 占位图或者失败图都可以直接使用R.color.white 颜色来做；
                .placeholder(R.mipmap.ic_launcher)
                //DiskCacheStrategy.NONE 什么都不缓存
                //DiskCacheStrategy.SOURCE 仅仅只缓存原来的全分辨率的图像
                //DiskCacheStrategy.RESULT 仅仅缓存最终的图像，即降低分辨率后的（或者是转换后的）
                //DiskCacheStrategy.ALL 缓存所有版本的图像（默认行为）
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }


}
