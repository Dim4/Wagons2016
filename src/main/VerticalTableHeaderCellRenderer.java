package main;

import sun.swing.table.DefaultTableCellHeaderRenderer;

import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import javax.swing.Icon;
import javax.swing.JTable;
import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;
import javax.swing.UIManager;


/**
 * Created by DM on 27.11.2016.
 */
public class VerticalTableHeaderCellRenderer extends DefaultTableHeaderCellRenderer {

    /**
     * Constructs a <code>VerticalTableHeaderCellRenderer</code>.
     * <P>
     * The horizontal and vertical alignments and text positions are set as
     * appropriate to a vertical table header cell.
     */
    public VerticalTableHeaderCellRenderer() {
        setHorizontalAlignment(LEFT);
        setHorizontalTextPosition(CENTER);
        setVerticalAlignment(CENTER);
        setVerticalTextPosition(TOP);
        setUI(new VerticalLabelUI());
        setPreferredSize(new Dimension(-1, 110));
    }

    /**
     * Overridden to return a rotated version of the sort icon.
     *
     * @param table the <code>JTable</code>.
     * @param column the colummn index.
     * @return the sort icon, or null if the column is unsorted.
     */
    @Override
    protected Icon getIcon(JTable table, int column) {
        SortKey sortKey =  getSortKey(table, column);
        if (sortKey != null && sortKey.getColumn() == column) {
            SortOrder sortOrder = sortKey.getSortOrder();
            switch (sortOrder) {
                case ASCENDING:
                    return VerticalSortIcon.ASCENDING;
                case DESCENDING:
                    return VerticalSortIcon.DESCENDING;
            }
        }
        return null;
    }

    /**
     * An icon implementation to paint the contained icon rotated 90?clockwise.
     * <P>
     * This implementation assumes that the L&F provides ascending and
     * descending sort icons of identical size.
     */
    private enum VerticalSortIcon implements Icon {

        ASCENDING,
        DESCENDING;
        private Icon icon = UIManager.getIcon("Table.ascendingSortIcon");

        /**
         * Paints an icon suitable for the header of a sorted table column,
         * rotated by 90?clockwise.  This rotation is applied to compensate
         * the rotation already applied to the passed in Graphics reference
         * by the VerticalLabelUI.
         * <P>
         * The icon is retrieved from the UIManager to obtain an icon
         * appropriate to the L&F.
         *
         * @param c the component to which the icon is to be rendered
         * @param g the graphics context
         * @param x the X coordinate of the icon's top-left corner
         * @param y the Y coordinate of the icon's top-left corner
         */
        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            switch (this) {
                case ASCENDING:
                    icon = UIManager.getIcon("Table.ascendingSortIcon");
                    break;
                case DESCENDING:
                    icon = UIManager.getIcon("Table.descendingSortIcon");
                    break;
            }
            int maxSide = Math.max(getIconWidth(), getIconHeight());
            Graphics2D g2 = (Graphics2D) g.create(x, y, maxSide, maxSide);
            g2.rotate((Math.PI / 2));
            g2.translate(0, -maxSide);
            icon.paintIcon(c, g2, 0, 0);
            g2.dispose();
        }

        /**
         * Returns the width of the rotated icon.
         *
         * @return the <B>height</B> of the contained icon
         */
        @Override
        public int getIconWidth() {
            return icon.getIconHeight();
        }

        /**
         * Returns the height of the rotated icon.
         *
         * @return the <B>width</B> of the contained icon
         */
        @Override
        public int getIconHeight() {
            return icon.getIconWidth();
        }
    }


}
