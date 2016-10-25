/**
 * Created by Zoe on 10/15/16.
 */
public class GameWindow {
    Connect2048_v2 model;
    GameWindowView view;

    public GameWindow(){
        model = new Connect2048_v2(this);
        view = new GameWindowView(this);
    }

    public static void main(String[] args) {
        GameWindow game = new GameWindow();
    }

    public Connect2048_v2 getModel(){return model;}
    public GameWindowView getView() {return view;}

}
