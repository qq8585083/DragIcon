package com.qq8585083.dragicon;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MainActivity me = this;
    private  SuperGridView mGridView;
    private List<HashMap<String, Object>> dataSourceList = new ArrayList<>();
    private List<HashMap<String, Object>> dataSourceListNew = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        initdata();
    }




    private void initview() {

        mGridView = (SuperGridView) findViewById(R.id.superGridView);
    }
    private void initdata() {
        for (int i = 0; i < 94; i++) {
            HashMap<String, Object> itemHashMap = new HashMap<>();
            itemHashMap.put("item_text", "Item " + Integer.toString(i));
            dataSourceList.add(itemHashMap);
        }

        for (int i = 0; i < 34; i++) {
            HashMap<String, Object> itemHashMap = new HashMap<>();
            itemHashMap.put("item_text", "New " + Integer.toString(i));
            dataSourceListNew.add(itemHashMap);
        }
        List<HashMap<String, Object>> hAf = new ArrayList<>();
        HashMap<String, Object> h1 = new HashMap<>();
        h1.put("item_text", "Header 1");
        HashMap<String, Object> h2 = new HashMap<>();
        h2.put("item_text", "Header 2");
        HashMap<String, Object> f1 = new HashMap<>();
        f1.put("item_text", "Footer 1");
        HashMap<String, Object> f2 = new HashMap<>();
        f2.put("item_text", "Footer 2");
        hAf.add(h1);
        hAf.add(h2);
        hAf.add(f1);
        hAf.add(f2);
        final SimpleAdapter mSimpleAdapter = new SimpleAdapter(me,
                dataSourceList, R.layout.dev_supergridview_item_griditem,
                new String[] {"item_text" }, new int[] {R.id.item_text });
        final SimpleAdapter mSimpleAdapterNew = new SimpleAdapter(me,
                dataSourceListNew, R.layout.dev_supergridview_item_griditem,
                new String[] {"item_text" }, new int[] {R.id.item_text });

        final SimpleAdapter hAfA = new SimpleAdapter(me, hAf,
                R.layout.dev_supergridview_item_griditem, new String[] {"item_text" },
                new int[] { R.id.item_text });
        final View viewHeaderItem1 = hAfA.getView(0, null, null);
        final View viewHeaderItem2 = hAfA.getView(1, null, null);
        final View header = View.inflate(me, R.layout.dev_supergridview_item_header, null);
        ((Button) header.findViewById(R.id.button1)).setText("添加头部子条目");
        ((Button) header.findViewById(R.id.button2)).setText("添加Adapter");
        viewHeaderItem1.setOnClickListener(new View.OnClickListener() {
            private boolean once = true;

            @Override
            public void onClick(View v) {
                if (once) {
                    once = false;
                    mGridView.removeHeaderView(header);
                } else {
                    once = true;
                    mGridView.addHeaderView(header);
                }
            }
        });
        header.findViewById(R.id.button1).setOnClickListener(
                new View.OnClickListener() {
                    private boolean once = false;

                    @Override
                    public void onClick(View v) {
                        if (once) {
                            once = false;
                            mGridView.removeHeaderItem(viewHeaderItem2);
                            ((Button) v).setText("添加头部子条目");
                        } else {
                            once = true;
                            mGridView
                                    .addHeaderItem(viewHeaderItem2, null, true);
                            ((Button) v).setText("删除头部子条目");
                        }

                    }
                });
        header.findViewById(R.id.button2).setOnClickListener(
                new View.OnClickListener() {
                    private boolean once = true;

                    @Override
                    public void onClick(View v) {
                        if (once) {
                            once = false;
                            mGridView.setAdapter(mSimpleAdapterNew);
                            mGridView.setDragable(false);
                        } else {
                            once = true;
                            mGridView.setAdapter(mSimpleAdapter);
                            mGridView.setDragable(true);
                        }
                    }
                });
        final View viewFooterItem1 = hAfA.getView(2, null, null);
        final View viewFooterItem2 = hAfA.getView(3, null, null);
        final View footer = View.inflate(me, R.layout.dev_supergridview_item_header, null);
        ((Button) footer.findViewById(R.id.button1)).setText("添加底部部子条目");
        ((Button) footer.findViewById(R.id.button2)).setText("改变Adapter");
        viewFooterItem1.setOnClickListener(new View.OnClickListener() {
            private boolean once = true;

            @Override
            public void onClick(View v) {
                if (once) {
                    once = false;
                    mGridView.removeFooterView(footer);
                } else {
                    once = true;
                    mGridView.addFooterView(footer);
                }
            }
        });
        footer.findViewById(R.id.button1).setOnClickListener(
                new View.OnClickListener() {
                    private boolean once = false;

                    @Override
                    public void onClick(View v) {
                        if (once) {
                            once = false;
                            mGridView.removeFooterItem(viewFooterItem2);
                            ((Button) v).setText("添加底部部子条目");
                        } else {
                            once = true;
                            mGridView
                                    .addFooterItem(viewFooterItem2, null, true);
                            ((Button) v).setText("删除底部部子条目");
                        }

                    }
                });

        footer.findViewById(R.id.button2).setOnClickListener(
                new View.OnClickListener() {
                    private boolean once = true;

                    @Override
                    public void onClick(View v) {
                        if (once) {
                            once = false;
                            dataSourceList.remove(dataSourceList.size() - 1);
                            mSimpleAdapter.notifyDataSetChanged();
                        } else {
                            once = true;
                            HashMap<String, Object> itemHashMap = new HashMap<>();
                            itemHashMap.put(
                                    "item_text",
                                    "Item "
                                            + Integer.toString(dataSourceList
                                            .size()));
                            dataSourceList.add(itemHashMap);
                            mSimpleAdapter.notifyDataSetChanged();
                        }
                    }
                });
        mGridView.addHeaderItem(viewHeaderItem1);
        mGridView.addFooterItem(viewFooterItem1);
        mGridView.addHeaderView(header);
        mGridView.setAdapter(mSimpleAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(me, "Click" + position, Toast.LENGTH_SHORT).show();
            }
        });
        mGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                Toast.makeText(me, "LongClick" + position, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        mGridView.setOnDragListener(new SuperGridView.OnDragListener() {

            @Override
            public void onMerge(int position, int toPoaition) {
                Toast.makeText(me, "合并" + position + "到" + toPoaition, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDelete(int position,
                                 int fristWrappedAdapterItemPosition,
                                 SuperGridView.DeleteAnimator deleteAnimator) {
                // 当添加HeaderView或HeaderItem后position有偏移
                deleteAnimator.delete();
                //deleteAnimator.deleteWithoutAnimation();
                //deleteAnimator.cancel();
                dataSourceList.remove(position
                        - fristWrappedAdapterItemPosition);
                mSimpleAdapter.notifyDataSetChanged();
            }
        });
        mGridView.setDeleteResource(R.mipmap.dev_ic_delete);
        // 设置删除位置
        mGridView.setDeleteDrawableLocation(0);
        mGridView.setStartScale(1.2f);
        mGridView.setDuration(180);
        mGridView.setDragController(new DragController() {
            @Override
            public Bitmap setDragView(View tagView) {
                tagView.setDrawingCacheEnabled(true);
                Bitmap bitmap = Bitmap.createBitmap(tagView.getDrawingCache());
                tagView.destroyDrawingCache();
                return bitmap;
            }
        });
    }

}
