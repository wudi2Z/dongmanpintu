package game1;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Music {
    private Player player;

    // 构造方法：只加载音乐文件，异常内部处理
    public Music() {
        try {
            File musicFile = new File("Music/music.mp3");
            FileInputStream fis = new FileInputStream(musicFile);
            player = new Player(fis);
        } catch (FileNotFoundException | JavaLayerException e) {
            System.out.println("背景音乐加载失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    // 播放方法：新开子线程执行，不阻塞界面
    public void play() {
        if (player == null) {
            System.out.println("音乐未加载成功，无法播放");
            return;
        }
        new Thread(() -> {
            try {
                player.play();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
