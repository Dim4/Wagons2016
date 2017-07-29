package main;

import javax.swing.*;
import javax.swing.table.*;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DM on 09.01.2017.
 */
public class CreateBigTableClass {
    private JTable tableJointTable;
    private java.sql.Timestamp ts15;
    private java.sql.Timestamp ts17;
    private String stringStart;

    public JTable getTableJointTable() {
        return tableJointTable;
    }

    public Timestamp getTs15() {
        return ts15;
    }

    public Timestamp getTs17() {
        return ts17;
    }

    public String getStringStart() {
        return stringStart;
    }

    public CreateBigTableClass (String spinnerStartStr, String spinnerEndStr, FormTwo parentFrame) {
//        JointTableClass jtc = new JointTableClass(spinnerStartStr, spinnerEndStr, parentFrame);

        JointTableClass jtc = new JointTableClass(spinnerStartStr, spinnerEndStr, parentFrame);


        final DefaultTableModel dTableModelJointTable = jtc.getJointTable();

        stringStart = jtc.getStringStart();

//        labelStart.setText(jtc.getStringStart());
//        textFieldStart.setText(jtc.getStringStart());
        ts15 = jtc.getTs15();
        ts17 = jtc.getTs17();

        tableJointTable = new JTable(dTableModelJointTable);
        System.out.println("Number of colums in table model: " + dTableModelJointTable.getColumnCount());

        ((DefaultTableCellRenderer) tableJointTable.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);

        TableCellRenderer headerRenderer = new VerticalTableHeaderCellRenderer();
        System.out.println("Number of columns in joint table 2: " + tableJointTable.getColumnCount());

        tableJointTable.getTableHeader().setDefaultRenderer(headerRenderer);
        tableJointTable.getTableHeader().setAutoscrolls(true);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);

        tableJointTable.getColumn(tableJointTable.getColumnName(1)).setCellRenderer( rightRenderer );
        tableJointTable.getColumn(tableJointTable.getColumnName(2)).setCellRenderer( rightRenderer );
        tableJointTable.getColumn(tableJointTable.getColumnName(3)).setCellRenderer( rightRenderer );

        tableJointTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableJointTable.getColumnModel().getColumn(1).setPreferredWidth(50);
        tableJointTable.getColumnModel().getColumn(2).setPreferredWidth(50);
        tableJointTable.getColumnModel().getColumn(3).setPreferredWidth(50);

        for (int i = 4; i < tableJointTable.getColumnCount(); i++) {
            tableJointTable.getColumnModel().getColumn(i).setPreferredWidth(20);
        }

        tableJointTable.setRowHeight(tableJointTable.getRowHeight() + 10);
        tableJointTable.setAutoCreateRowSorter(true);
        tableJointTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableJointTable.getModel());
        tableJointTable.setRowSorter(sorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);

        tableJointTable.setEnabled(false);
        tableJointTable.setFillsViewportHeight(true);

//        scrollBigTable.setViewportView(tableJointTable);
//        FixedColumnTable fct = new FixedColumnTable(1, scrollBigTable);
//
//        fct.getFixedTable().setAutoCreateRowSorter(true);
//        fct.getFixedTable().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//
//        TableRowSorter<TableModel> sorter2 = new TableRowSorter<TableModel>(fct.getFixedTable().getModel());
//        fct.getFixedTable().setRowSorter(sorter2);
//
//        List<RowSorter.SortKey> sortKeys2 = new ArrayList<>(25);
//        sortKeys2.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
//        sorter2.setSortKeys(sortKeys2);
    }

    public CreateBigTableClass (String spinnerStartStr, String spinnerEndStr, FormTwo parentFrame, Connection conn) {
//        JointTableClass jtc = new JointTableClass(spinnerStartStr, spinnerEndStr, parentFrame);

        JointTableClass jtc = new JointTableClass(spinnerStartStr, spinnerEndStr, parentFrame, conn);


        final DefaultTableModel dTableModelJointTable = jtc.getJointTable();

        stringStart = jtc.getStringStart();

//        labelStart.setText(jtc.getStringStart());
//        textFieldStart.setText(jtc.getStringStart());
        ts15 = jtc.getTs15();
        ts17 = jtc.getTs17();

        tableJointTable = new JTable(dTableModelJointTable);
        System.out.println("Number of colums in table model: " + dTableModelJointTable.getColumnCount());

        ((DefaultTableCellRenderer) tableJointTable.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);

        TableCellRenderer headerRenderer = new VerticalTableHeaderCellRenderer();
        System.out.println("Number of columns in joint table 2: " + tableJointTable.getColumnCount());

        tableJointTable.getTableHeader().setDefaultRenderer(headerRenderer);
        tableJointTable.getTableHeader().setAutoscrolls(true);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);

        tableJointTable.getColumn(tableJointTable.getColumnName(1)).setCellRenderer( rightRenderer );
        tableJointTable.getColumn(tableJointTable.getColumnName(2)).setCellRenderer( rightRenderer );
        tableJointTable.getColumn(tableJointTable.getColumnName(3)).setCellRenderer( rightRenderer );

        tableJointTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableJointTable.getColumnModel().getColumn(1).setPreferredWidth(50);
        tableJointTable.getColumnModel().getColumn(2).setPreferredWidth(50);
        tableJointTable.getColumnModel().getColumn(3).setPreferredWidth(50);

        for (int i = 4; i < tableJointTable.getColumnCount(); i++) {
            tableJointTable.getColumnModel().getColumn(i).setPreferredWidth(20);
        }

        tableJointTable.setRowHeight(tableJointTable.getRowHeight() + 10);
        tableJointTable.setAutoCreateRowSorter(true);
        tableJointTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableJointTable.getModel());
        tableJointTable.setRowSorter(sorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);

//        tableJointTable.setEnabled(false);
//        tableJointTable.setFillsViewportHeight(true);

    }
}
