package com.example.jackiechan.dbutils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.xutils.DbManager;
import org.xutils.common.util.LogUtil;
import org.xutils.x;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig();
        x.view().inject(this);
        daoConfig.setDbName("test");//设置数据库文件的名字
        daoConfig.setDbVersion(1);//设置数据库的版本
        daoConfig.setDbUpgradeListener(new DbManager.DbUpgradeListener() {//设置数据库升级的监听事件
            @Override
            public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                LogUtil.e("数据库升级了===old" + oldVersion + "=====" + newVersion);
                //关于数据库升级的注意事项
                /**
                 * 1: 数据库升级的话一般是增加某些字段,可以通过 alert 这个SQL 语句修改表,增加对应的字段,另外也可以通过 db.addColum 这个方法给对应的表增加字段
                 * 2:升级的时候应当判断版本号,因为有些用户可能是跨越多个版本更新的,比如从软件1直接升级到了2,中间的1.x没有更新过,但是这个数据库中表的字段增加是以1.x 中某个版本为准的
                 * 1这个版本无法直接修改升级到最新的2,那应该怎么办呢? 可以用 sql 语句 drop 掉这张表,然后创建新的表进去,但是在 drop 之前应该保存原始的数据,然后将对应的数据通过对应的方法重新加回到新的数据库中
                 * 中间可能会牵扯到字段变化等事项,需要自己注意下原始数据的某个字段应该添加到新的什么字段
                 * 另外可以通过 db.dropTable(Person.class);//删除某张表然后重新创建,但是也得注意保存数据
                 *
                 */
            }
        });
        daoConfig.setAllowTransaction(true);//是否开启事物的支持
//        daoConfig.setDbDir(new File(Environment.getExternalStorageDirectory()+"/Test"));//设置数据库目录路径,直接将数据库放到sd 卡目录中
        DbManager db = x.getDb(daoConfig);//创建完管理者了
    }
}
