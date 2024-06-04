package Core;

import Characters.Character;

public class Collision {

    GamePanel gamePanel;


    public Collision(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Character character) {
        int characterLeftWorldX = character.worldX + character.solidArea.x;
        int characterRightWorldX = characterLeftWorldX + character.solidArea.width;
        int characterUpWorldY = character.worldY + character.solidArea.y;
        int characterDownWorldY = characterUpWorldY + character.solidArea.height;

        int characterLeftCol = characterLeftWorldX / gamePanel.tileSize;
        int characterRightCol = characterRightWorldX / gamePanel.tileSize;
        int characterUpRow = characterUpWorldY / gamePanel.tileSize;
        int characterDownRow = characterDownWorldY / gamePanel.tileSize;

        int tileNum1, tileNum2;

        switch (character.direction) {
            case "up":
                characterUpRow = (characterUpWorldY - character.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[characterLeftCol][characterUpRow];
                tileNum2 = gamePanel.tileManager.mapTileNumber[characterRightCol][characterUpRow];
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    character.collisionOn = true;
                }
                break;
            case "down":
                characterDownRow = (characterDownWorldY + character.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[characterLeftCol][characterDownRow];
                tileNum2 = gamePanel.tileManager.mapTileNumber[characterRightCol][characterDownRow];
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    character.collisionOn = true;
                }
                break;
            case "left":
                characterLeftCol = (characterLeftWorldX - character.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[characterLeftCol][characterUpRow];
                tileNum2 = gamePanel.tileManager.mapTileNumber[characterLeftCol][characterDownRow];
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    character.collisionOn = true;
                }
                break;
            case "right":
                characterRightCol = (characterRightWorldX + character.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[characterRightCol][characterUpRow];
                tileNum2 = gamePanel.tileManager.mapTileNumber[characterRightCol][characterDownRow];
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    character.collisionOn = true;
                }
                break;
        }
    }
    public int checkObject(Character character, boolean player){
        int index = 999;

        for (int i = 0; i < gamePanel.obj.length; i++){
            if(gamePanel.obj[i] != null) {
                character.solidArea.x = character.worldX + character.solidAreaDeafultX;
                character.solidArea.y = character.worldY + character.solidAreaDeafultY;

                gamePanel.obj[i].solidArea.x = gamePanel.obj[i].worldX + gamePanel.obj[i].solidAreaDefaultX;
                gamePanel.obj[i].solidArea.y = gamePanel.obj[i].worldY + gamePanel.obj[i].solidAreaDefaultY;

                switch (character.direction){
                    case "up":
                        character.solidArea.y -= character.speed;
                        if(character.solidArea.intersects(gamePanel.obj[i].solidArea)){
                        if ( gamePanel.obj[i].collision){
                            character.collisionOn=true;
                        }
                        if(player){
                            index=i;
                        }
                        }
                        break;
                    case "down":
                        character.solidArea.y += character.speed;
                        if(character.solidArea.intersects(gamePanel.obj[i].solidArea)){
                            if ( gamePanel.obj[i].collision){
                                character.collisionOn=true;
                            }
                            if(player){
                                index=i;
                            }

                        }
                        break;
                    case "left":
                        character.solidArea.x -= character.speed;
                        if(character.solidArea.intersects(gamePanel.obj[i].solidArea)){
                            if ( gamePanel.obj[i].collision){
                                character.collisionOn=true;
                            }
                            if(player){
                                index=i;
                            }

                        }
                        break;
                    case "right":
                        character.solidArea.x += character.speed;
                        if(character.solidArea.intersects(gamePanel.obj[i].solidArea)){
                            if ( gamePanel.obj[i].collision){
                                character.collisionOn=true;
                            }
                            if(player){
                                index=i;
                            }

                        }
                        break;
                }

                // Reset the coordinates to their default values
                character.solidArea.x = character.solidAreaDeafultX;
                character.solidArea.y = character.solidAreaDeafultY;
                gamePanel.obj[i].solidArea.x = gamePanel.obj[i].solidAreaDefaultX;
                gamePanel.obj[i].solidArea.y = gamePanel.obj[i].solidAreaDefaultY;
            }
        }
        return index;
    }
}