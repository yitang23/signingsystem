package com.example.test8;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.baidu.location.PoiRegion;
import com.baidu.location.BDLocationListener;
import com.baidu.location.Poi;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.BDLocation;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity   {

    private TextView locationInfoTextView = null;
    private Button startButton = null;
    private LocationClient locationClient = null;
    private static final int UPDATE_TIME = 5000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationInfoTextView = (TextView) this.findViewById(R.id.text2);
        startButton = (Button) this.findViewById(R.id.btn_start);
        locationClient = new LocationClient(this);
        //设置定位条件
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);
        option.setIsNeedLocationPoiList(true);
//可选，是否需要周边POI信息，默认为不需要，即参数为false
//如果开发者需要获得周边POI信息，此处必须为true

        option.setIsNeedAddress(true);//获取位置
        option.setIsNeedLocationDescribe(true);
//可选，是否需要位置描述信息，默认为不需要，即参数为false
//如果开发者需要获得当前点的位置信息，此处必须为true

        //是否打开GPS
        option.setCoorType("bd09ll");
//可选，设置返回经纬度坐标类型，默认GCJ02
//GCJ02：国测局坐标；
//BD09ll：百度经纬度坐标；
//BD09：百度墨卡托坐标；
//海外地区定位，无需设置坐标类型，统一返回WGS84类型坐标
        option.setPriority(LocationClientOption.NetWorkFirst);
        //设置定位优先级
        option.setProdName("LocationDemo");
        //设置产品线名称
        option.setScanSpan(UPDATE_TIME);
        //设置定时定位的时间间隔。单位毫秒
        locationClient.setLocOption(option);

        //注册位置监听器
        locationClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation location) {
                int tag=2;

                if (location == null) {
                    return;
                }
                StringBuffer sb = new StringBuffer(256);
                sb.append("Time : ");
                sb.append(location.getTime());//获取定位时间
                sb.append("\nError code : ");
                sb.append(location.getLocType());//获取类型
                sb.append("\nLatitude : ");
                sb.append(location.getLatitude());//获取纬度信息
                sb.append("\nLongitude : ");
                sb.append(location.getLongitude());//获取经度信息
                sb.append("\nRadius : ");
                sb.append(location.getRadius());//获取定位精准度
                sb.append("\npoi:");
                //sb.append(location.getPoiList());
               /*
                if (location.getPoiList() != null && !location.getPoiList().isEmpty()) {
                    for (int i = 0; i < location.getPoiList().size(); i++) {
                        Poi poi = (Poi) location.getPoiList().get(i);
                        sb.append("poiName:");
                        sb.append(poi.getName() + ", ");
                        sb.append("poiTag:");
                        sb.append(poi.getTags() + "\n");
                    }
                }
                */

                if (location.getPoiRegion() != null) {
                    sb.append("PoiRegion: ");// 返回定位位置相对poi的位置关系，仅在开发者设置需要POI信息时才会返回，在网络不通或无法获取时有可能返回null
                    PoiRegion poiRegion = location.getPoiRegion();
                    sb.append("DerectionDesc:"); // 获取POIREGION的位置关系，ex:"内"
                    sb.append(poiRegion.getDerectionDesc() + "; ");
                    sb.append("Name:"); // 获取POIREGION的名字字符串
                    sb.append(poiRegion.getName() + "; ");
                    sb.append("Tags:"); // 获取POIREGION的类型
                    sb.append(poiRegion.getTags() + "; ");
                    //sb.append("\nSDK版本: ");
                }

                if (location.getLocType() == BDLocation.TypeGpsLocation) {
                    sb.append("\nSpeed : ");
                    sb.append(location.getSpeed());
                    sb.append("\nSatellite : ");
                    sb.append(location.getSatelliteNumber());
                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
                    sb.append("\nAddress : ");
                    sb.append(location.getAddrStr());
                }

                locationInfoTextView.setText(sb.toString());
            }

        });
        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (locationClient == null) {
                    return;
                }
                if (locationClient.isStarted()) {
                    startButton.setText("Start");
                    locationClient.stop();
                }
                else {
                    startButton.setText("Stop");
                    locationClient.start();
                    locationClient.requestLocation();

                }
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationClient != null && locationClient.isStarted()) {
            locationClient.stop();
            locationClient = null;
        }
    }





}