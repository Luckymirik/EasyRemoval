import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Main extends JFrame {
    static  JFrame frame = new JFrame();
    static JLayeredPane pane = new JLayeredPane();
    static public void add(MouseEvent e) throws IOException {
        if (e.getButton() == 1){
            BufferedImage image = ImageIO.read(new URL("https://tgram.ru/wiki/stickers/img/vmoji_meme/png/15.png"));
            JLabel label = new JLabel(new ImageIcon(image));
            label.setBounds(e.getX(), e.getY(), image.getWidth(), image.getHeight());
            label.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e){
                    delete(e);

                }
            });
            pane.add(label);
        }
    }
    public static void delete(MouseEvent e){
        if (e.getButton()==2){
            pane.remove((JLabel) e.getSource());
            pane.repaint();
        }
    }
    public static void main(String[] args) throws IOException {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Простое удаление");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = 800, height = 800;
        frame.setBounds(dimension.width/2-width/2,dimension.height/2-height/2,width,height);



        pane.setFocusable(true);
        frame.add(pane);
        pane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    add(e);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        frame.setVisible(true);

    }
}