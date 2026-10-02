package game1;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class PictureFrame extends JFrame {
    //生成空参构造方法
    public PictureFrame() {
        initFrame();
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
        int[][] datas = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}};
        //打乱数组
//        daluan(datas);

        //创建一个面板，用于存放图片
        JPanel imagePanel = new JPanel();
        imagePanel.setBounds(150,114,360,360);
        imagePanel.setLayout(null);


        //绘制数组
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                JLabel jLabel =new JLabel(new ImageIcon("images/"+datas[i][j]+".png"));
                jLabel.setBounds(j*90,i*90,90,90);
                this.add(jLabel);
            }
        }
        this.add(imagePanel);

        //参照图
        JLabel canZhaoTuLabel=new JLabel(new ImageIcon("images/canzhaotu.png"));
        canZhaoTuLabel.setBounds(574,114,122,121);
        this.add(canZhaoTuLabel);
    }

    public static void daluan(int[][] datas){
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