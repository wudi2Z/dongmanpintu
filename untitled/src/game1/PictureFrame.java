package game1;

import javax.swing.*;
import java.util.Random;

public class PictureFrame extends JFrame {

    private int[][] datas = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}

    };
    //生成空参构造方法
    public PictureFrame() {
        initFrame();
        //在图片绘制前打乱窗口数组
        Random(datas);
        paintView();//绘制视图
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
    }

    public void paintView() {
        //绘制视图

        //打乱数组
        //Random(datas);

        //创建一个面板，用于存放图片
        JPanel imagePanel = new JPanel();
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
        JButton shangButton=new JButton(new ImageIcon("images/shang.png"));
        shangButton.setBounds(732,265,57,57);
        this.add(shangButton);
        //添加左按钮
        JButton zuoButton=new JButton(new ImageIcon("images/zuo.png"));
        zuoButton.setBounds(650,347,57,57);
        this.add(zuoButton);
        //添加下按钮
        JButton xiaButton=new JButton(new ImageIcon("images/xia.png"));
        xiaButton.setBounds(732,347,57,57);
        this.add(xiaButton);
        //添加右按钮
        JButton youButton=new JButton(new ImageIcon("images/you.png"));
        youButton.setBounds(813,347,57,57);
        this.add(youButton);
        //求助按钮
        JButton qiuZhuButton=new JButton(new ImageIcon("images/qiuzhu.png"));
        qiuZhuButton.setBounds(626, 444, 108, 45);
        this.add(qiuZhuButton);
        //重置按钮
        JButton chongZhiButton=new JButton(new ImageIcon("images/chongzhi.png"));
        chongZhiButton.setBounds(786, 444, 108, 45);
        this.add(chongZhiButton);
        //背景图: 必须写在最后
        JLabel backgroundLabel = new JLabel(new ImageIcon("images/background.png"));
        backgroundLabel.setBounds(0, 0, 968, 530);
        this.add(backgroundLabel);
    }

    public static void Random(int[][] datas){
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
    }
}