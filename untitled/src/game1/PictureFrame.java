package game1;

import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Random;

/*
1. 创建数组以此来索引图片，这样显示出来就是1-16的图片编号
    JLabel jLabel=new JLabel(ImageIcon icon)
    JLabel jLabel=new JLabel(new ImageIcon("image/+datas[i][j]+".png"));

2.打乱数组然后用JLabel重新打印打乱后的图片

3.创建JPanel来存放图片标签
    JPanel imagePanel=new JPanel();
    imagePanel.setLayout(null);//取消默认布局

 4.创建按钮监听事件来移动图片

 5.判断游戏是否成功
 */
public class PictureFrame extends JFrame {

    private int[][] datas = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}

    };

    private int[][] winDatas = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}

    };

    //定义空白图片索引以此来交换
    private int x0,y0;
    //将上下左右，求助，重置等按钮提到成员变量，不然后面方法中不能直接使用
    private JButton shangButton;
    private JButton zuoButton;
    private JButton xiaButton;
    private JButton youButton;
    private JButton qiuZhuButton;
    private JButton chongZhiButton;

    private JPanel imagePanel;

    private void rePaintView(){
        //1.将paintView中的imagePanel提升到成员位置
        //2.调用imagePanel的removeAll()方法移除所有组件
        imagePanel.removeAll();
        //3.重新绘制视图
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                JLabel imageLabel = new JLabel(new ImageIcon("images/"+ datas[i][j] + ".png"));
                imageLabel.setBounds(j * 90, i * 90, 90, 90);
                imagePanel.add(imageLabel);
            }
        }
        this.add(imagePanel);
        //4.刷新视图
        imagePanel.revalidate();
        imagePanel.repaint();

    }

    //求助实现的方法
    private void success() {
        //将二维数组datas元素重置为,编号为1到16的图片
        datas = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        //设置上左下右按钮失效
        shangButton.setEnabled(false); //java提供
        youButton.setEnabled(false);
        xiaButton.setEnabled(false);
        zuoButton.setEnabled(false);
    }

    //判断游戏是否成功
    public boolean isSuccess(){
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                //如果有一个元素不一样,返回false
                if(datas[i][j]!=winDatas[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    private void addButtonEvent(){
        xiaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //上会有边界即x0=3, 不能下移
                if(x0==3){
                    System.out.println("不能下移");

                    return;
                }
                //下移
                datas[x0][y0]=datas[x0+1][y0];
                datas[x0+1][y0]=0;
                x0=x0+1;
                //3.重绘方法
                if (isSuccess()){
                    success();
                }
                rePaintView();
            }
        });
        youButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(y0==3){
                    System.out.println("不能右移");
                    return;
                }
                datas[x0][y0]=datas[x0][y0+1];
                datas[x0][y0+1]=0;
                y0=y0+1;
                //3.重绘方法
                if (isSuccess()){
                    success();
                }
                rePaintView();
            }
        });
        shangButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(x0==0){
                    System.out.println("不能上移");
                    return;
                }
                datas[x0][y0]=datas[x0-1][y0];
                datas[x0-1][y0]=0;
                x0=x0-1;
                //重绘画板
                if(isSuccess()){
                    success();
                }
                rePaintView();
            }
        });
        zuoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(y0==0){
                    System.out.println("不能左移");
                    return;
                }
                datas[x0][y0]=datas[x0][y0-1];
                datas[x0][y0-1]=0;
                y0=y0-1;
               //调用方法判断是否成功
                if (isSuccess()){
                    success();
                }
                //3.重绘方法
                rePaintView();
            }
        });
        qiuZhuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                success();
                rePaintView();
            }
        });
        chongZhiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("点击了重置按钮");
            }
        });
    }

    //生成空参构造方法
    public PictureFrame() {
        initFrame();
        //在图片绘制前打乱窗口数组
        randomDate(datas);
        paintView();//绘制视图
        addButtonEvent();//添加按钮事件
        setVisible(true);//设置窗口可见
    }

    public void initFrame() {
        this.setTitle("动漫拼图");//设置窗口标题
        this.setSize(960, 565);
        this.setLocationRelativeTo(null);//将窗口居中显示
        this.setDefaultCloseOperation(3);//设置默认关闭操作为退出程序
        this.setResizable(false);//设置窗口不可调整大小
        this.setAlwaysOnTop(true);//设置窗口总是在顶部
        this.setLayout(null);//设置布局为null，即不使用布局管理器
       Music bgm=new Music();
       bgm.play();
    }

    public void paintView() {
        //绘制视图

        //打乱数组
        //Random(datas);

        //创建一个面板，用于存放图片
        JLabel titleLabel=new JLabel(new ImageIcon("images/title.png"));
        titleLabel.setBounds(254, 27, 232, 57);
        this.add(titleLabel);

        imagePanel=new JPanel();
        imagePanel.setBounds(150,114,360,360);
        imagePanel.setLayout(null);


        //绘制数组
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                JLabel jLabel =new JLabel(new ImageIcon("images/"+datas[i][j]+".png"));
                jLabel.setBounds(j*90,i*90,90,90);
                imagePanel.add(jLabel);
            }
        }
        this.add(imagePanel);

        //参照图
        JLabel canZhaoTuLabel=new JLabel(new ImageIcon("images/canzhaotu.png"));
        canZhaoTuLabel.setBounds(574,114,122,121);
        this.add(canZhaoTuLabel);
        //添加上按钮
        shangButton=new JButton(new ImageIcon("images/shang.png"));
        shangButton.setBounds(732,265,57,57);
        this.add(shangButton);
        //添加左按钮
        zuoButton=new JButton(new ImageIcon("images/zuo.png"));
        zuoButton.setBounds(650,347,57,57);
        this.add(zuoButton);
        //添加下按钮
        xiaButton=new JButton(new ImageIcon("images/xia.png"));
        xiaButton.setBounds(732,347,57,57);
        this.add(xiaButton);
        //添加右按钮
        youButton=new JButton(new ImageIcon("images/you.png"));
        youButton.setBounds(813,347,57,57);
        this.add(youButton);
        //求助按钮
        qiuZhuButton=new JButton(new ImageIcon("images/qiuzhu.png"));
        qiuZhuButton.setBounds(626, 444, 108, 45);
        this.add(qiuZhuButton);
        //重置按钮
        chongZhiButton=new JButton(new ImageIcon("images/chongzhi.png"));
        chongZhiButton.setBounds(786, 444, 108, 45);
        this.add(chongZhiButton);
        //背景图: 必须写在最后
        JLabel backgroundLabel = new JLabel(new ImageIcon("images/background.png"));
        backgroundLabel.setBounds(0, 0, 968, 530);
        this.add(backgroundLabel);
    }


    public void randomDate(int[][] datas){
        Random r=new Random();
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                int temp=datas[i][j];
                int x=r.nextInt(datas.length);
                int y=r.nextInt(datas[i].length);
                datas[i][j]=datas[x][y];
                datas[x][y]=temp;

            }
        }
        //找到空白图片索引
        wc:
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                if(datas[i][j]==0){
                    x0=i;
                    y0=j;
                    break wc;
                }
            }
        }
        System.out.printf("空白图片索引为:(%d,%d)\n",x0,y0);
    }

}