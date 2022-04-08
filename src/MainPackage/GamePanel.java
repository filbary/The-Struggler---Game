package MainPackage;


import java.awt.*;
import java.awt.image.BufferStrategy;

public class GamePanel extends Canvas implements Runnable {

    private final int HEIGHT = 563;
    private final int WIDTH = 1000;
    private final String TITLE = "GIERA";

    private Thread thread;
    private boolean isRunning = false;
    private Handler handler;
    private LevelLoader levelLoader;
    private Camera camera;
    private UI ui;
    private Shadow shadow;
    private MouseHandler mouseHandler;

    private LevelHandler levelHandler;
    private Launcher launcher;

    public GamePanel(){
        new Window(TITLE, HEIGHT, WIDTH, this);
        this.handler = new Handler();
        this.setFocusable(true);
        this.addKeyListener(new KeyHandler(handler));
        this.addMouseMotionListener(mouseHandler);
        this.levelLoader = new LevelLoader(handler);

        levelLoader.initLevel(handler.getCurrentLevel());
        this.camera = new Camera(0, 0);
        this.ui = new UI(handler.player, camera);
        this.shadow = new Shadow(handler.player);
        this.mouseHandler = new MouseHandler(handler, camera, ui, levelLoader);
        this.addMouseListener(mouseHandler);
        this.levelHandler = new LevelHandler(handler, levelLoader, shadow, ui, camera);
        this.launcher = new Launcher(this);
        this.addKeyListener(new LauncherKeyHandler(launcher));

        start();
    }

//    public void startNewGame(){
//        levelLoader.initLevel(handler.getCurrentLevel());
//        this.camera = new Camera(0, 0);
//        this.ui = new UI(handler.player, camera);
//        this.shadow = new Shadow(handler.player);
//        this.mouseHandler = new MouseHandler(handler, camera, ui, levelLoader);
//        this.addMouseListener(mouseHandler);
//        this.levelHandler = new LevelHandler(handler, levelLoader, shadow, ui, camera);
//    }


    private void start(){
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }
    private void stop(){
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000/amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while(isRunning){
            long now = System.nanoTime();
            delta += (now-lastTime)/ns;
            lastTime = now;
            if (delta>=1){
                tick();
                updates+=1;
                delta = 0;       //changed from delta-=1 beacause too many operations are made after loading screen
            }
            render();
            frames+=1;

            if (System.currentTimeMillis()-timer>1000){
                timer+=1000;
                System.out.println(updates + " Ticks, FPS: " + frames);
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){
        if (!launcher.isOpened()) {
            camera.tick(handler.player);
            ui.tick();
            handler.tick();
            levelHandler.tick();
            if (levelHandler.startLauncher) {
                levelHandler.startLauncher = false;
                launcher.setOpened(true);
            }
        }
        else {
            launcher.tick();
        }
    }


    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        //////////////////////////////////

        if (!launcher.isOpened()) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 1000, 563);
            g.translate((int) -camera.getX(), (int) -camera.getY());
            handler.render(g);
            shadow.render(g);
            ui.render(g);
            levelHandler.render(g);
            g.translate((int) camera.getX(), (int) camera.getY());
        }
        else {
            launcher.render(g);
        }

        //////////////////////////////////
        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        new GamePanel();
    }

    public LevelLoader getLevelLoader() {
        return levelLoader;
    }

    public LevelHandler getLevelHandler() {
        return levelHandler;
    }
    public Handler getHandler() {
        return handler;
    }
}
