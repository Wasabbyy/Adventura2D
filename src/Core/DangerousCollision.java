package Core;

import Characters.Character;

public class DangerousCollision extends Collision{
    public DangerousCollision(GamePanel gamePanel) {
        super(gamePanel);
    }

    @Override
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
                    if (gamePanel.tileManager.tile[tileNum1].dangerousCollision || gamePanel.tileManager.tile[tileNum2].dangerousCollision) {
                        character.dangerousCollisionOn = true;
                    }
                    break;
                case "down":
                    characterDownRow = (characterDownWorldY + character.speed) / gamePanel.tileSize;
                    tileNum1 = gamePanel.tileManager.mapTileNumber[characterLeftCol][characterDownRow];
                    tileNum2 = gamePanel.tileManager.mapTileNumber[characterRightCol][characterDownRow];
                    if (gamePanel.tileManager.tile[tileNum1].dangerousCollision || gamePanel.tileManager.tile[tileNum2].dangerousCollision) {
                        character.dangerousCollisionOn = true;
                    }
                    break;
                case "left":
                    characterLeftCol = (characterLeftWorldX - character.speed) / gamePanel.tileSize;
                    tileNum1 = gamePanel.tileManager.mapTileNumber[characterLeftCol][characterUpRow];
                    tileNum2 = gamePanel.tileManager.mapTileNumber[characterLeftCol][characterDownRow];
                    if (gamePanel.tileManager.tile[tileNum1].dangerousCollision || gamePanel.tileManager.tile[tileNum2].dangerousCollision) {
                        character.dangerousCollisionOn = true;
                    }
                    break;
                case "right":
                    characterRightCol = (characterRightWorldX + character.speed) / gamePanel.tileSize;
                    tileNum1 = gamePanel.tileManager.mapTileNumber[characterRightCol][characterUpRow];
                    tileNum2 = gamePanel.tileManager.mapTileNumber[characterRightCol][characterDownRow];
                    if (gamePanel.tileManager.tile[tileNum1].dangerousCollision || gamePanel.tileManager.tile[tileNum2].dangerousCollision) {
                        character.dangerousCollisionOn = true;
                    }
                    break;
            }
        }

}
