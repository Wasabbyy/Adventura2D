package Object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends  SuperObject{

    public OBJ_Key(){
        name = "Firerock";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/firerock.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
