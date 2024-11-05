package Core;

import Characters.Spells;

public class SpellCollision extends Collision {
    public SpellCollision(GamePanel gamePanel) {
        super(gamePanel);
    }

    public void checkTile(Spells spells) {
        int spellLeftWorldX = spells.getWorldX() + spells.solidArea.x;
        int spellRightWorldX = spellLeftWorldX + spells.solidArea.width;
        int spellUpWorldY = spells.getWorldY() + spells.solidArea.y;
        int spellDownWorldY = spellUpWorldY + spells.solidArea.height;

        int spellLeftCol = spellLeftWorldX / gamePanel.tileSize;
        int spellRightCol = spellRightWorldX / gamePanel.tileSize;
        int spellUpRow = spellUpWorldY / gamePanel.tileSize;
        int spellDownRow = spellDownWorldY / gamePanel.tileSize;

        int tileNum1, tileNum2;

        switch (spells.getDirection()) {
            case "up":
                spellUpRow = (spellUpWorldY - spells.getSpeed()) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[spellLeftCol][spellUpRow];
                tileNum2 = gamePanel.tileManager.mapTileNumber[spellRightCol][spellUpRow];
                if (gamePanel.tileManager.tile[tileNum1].dangerousCollision || gamePanel.tileManager.tile[tileNum2].dangerousCollision) {
                    spells.setCollisionOn(true);
                }
                break;
            case "down":
                spellDownRow = (spellDownWorldY + spells.getSpeed()) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[spellLeftCol][spellDownRow];
                tileNum2 = gamePanel.tileManager.mapTileNumber[spellRightCol][spellDownRow];
                if (gamePanel.tileManager.tile[tileNum1].dangerousCollision || gamePanel.tileManager.tile[tileNum2].dangerousCollision) {
                    spells.setCollisionOn(true);
                }
                break;
            case "left":
                spellLeftCol = (spellLeftWorldX - spells.getSpeed()) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[spellLeftCol][spellUpRow];
                tileNum2 = gamePanel.tileManager.mapTileNumber[spellLeftCol][spellDownRow];
                if (gamePanel.tileManager.tile[tileNum1].dangerousCollision || gamePanel.tileManager.tile[tileNum2].dangerousCollision) {
                    spells.setCollisionOn(true);
                }
                break;
            case "right":
                spellRightCol = (spellRightWorldX + spells.getSpeed()) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[spellRightCol][spellUpRow];
                tileNum2 = gamePanel.tileManager.mapTileNumber[spellRightCol][spellDownRow];
                if (gamePanel.tileManager.tile[tileNum1].dangerousCollision || gamePanel.tileManager.tile[tileNum2].dangerousCollision) {
                    spells.setCollisionOn(true);
                }
                break;
        }
    }
}
