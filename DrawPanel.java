import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.Font;

class DrawPanel extends JPanel implements MouseListener {

    private ArrayList<Card> hand;

    // Rectangle object represents a rectangle
    // Allows us to check whether we clicked a specific rectangle
    private Rectangle button;

    public DrawPanel() {
        // Represents the get new cards button
        button = new Rectangle(147, 250, 160, 26);
        this.addMouseListener(this);
        hand = Card.buildHand();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 125;
        int y = 10;
        for (int i = 0; i < hand.size(); i++) {
            Card c = hand.get(i);

            if (c.getHighlight()) {

                // box around the card
                // draw the border rectangle around the card
                g.drawRect(x, y, c.getImage().getWidth(), c.getImage().getHeight());
            }

            // establish the location of the rectangle "hitbox"
            c.setRectangleLocation(x, y);


            g.drawImage(c.getImage(), x, y, null);
            x = x + c.getImage().getWidth() + 10;

            if (i == 2 || i == 5){
                x = 125;
                y = y + c.getImage().getHeight() + 10;
            }
        }

        // drawing the bottom button (3 code lines below)
        g.setFont(new Font("Courier New", Font.BOLD, 20));
        // new print statement: words, placement
        g.drawString("GET NEW CARDS", 150, 270);
        g.drawRect((int)button.getX(), (int)button.getY(), (int)button.getWidth(), (int)button.getHeight());
    }

    public void mousePressed(MouseEvent e) {

        Point clicked = e.getPoint();

        // left click (e.getButton() returns which button on the mouse got clicked)
        // 1 is left, 2 is middle, 3 is right
        if (e.getButton() == 1) {
            // takes a point (clicked) and tells you if it's inside button
            if (button.contains(clicked)) {
                hand = Card.buildHand();
            }

            // go thorough each card
            // check if any of them were clicked on
            // if it was clicked, flip the card
            for (int i = 0; i < hand.size(); i++) {
                Rectangle box = hand.get(i).getCardBox();
                if (box.contains(clicked)) {
                    hand.get(i).flipCard();
                }
            }
        }

        if (e.getButton() == 3) {
            for (int i = 0; i < hand.size(); i++) {
                Rectangle box = hand.get(i).getCardBox();
                if (box.contains(clicked)) {
                    if (hand.get(i).getHighlight()){
                        hand = Card.replaceCard(hand, hand.get(i));
                    }
                    hand.get(i).flipHighlight();
                }
            }
        }


    }

    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }
}