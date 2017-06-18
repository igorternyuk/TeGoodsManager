package tegoods.model;

import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import tegoods.model.exceptions.ProductNotFoundException;

/**
 *
 * @author igor
 */
public class TModel implements TableModel{
    public static final int COLUMN_ID = 0;
    public static final int COLUMN_NAME = 1;
    public static final int COLUMN_PRICE = 2;
    public static final int COLUMN_DATE = 3;
    private final List<Product> list;
    private final String[] titles = {"ID", "Name", "Price", "Add date"};
   
    public TModel(List<Product> list) {
        this.list = list;
    }
    
    public Product getProduct(int row) throws ProductNotFoundException{
        if(!list.isEmpty()){ 
            return list.get(row);
        } else {
            throw new ProductNotFoundException("There are no products in the database");
        }
    }
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return titles.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return titles[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(list.isEmpty()){
            return Object.class;
        } else {
            return getValueAt(0, columnIndex).getClass();
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Product p = list.get(rowIndex);
        Object result = null;
        switch(columnIndex){
            case COLUMN_ID :
                result = p.getId();
                break;
            case COLUMN_NAME :
                result = p.getName();
                break;
            case COLUMN_PRICE :
                result = p.getPrice();
                break;
            case COLUMN_DATE :
                result = p.getAddDate();
                break;
            default:
                throw new IllegalArgumentException("Invalid column index");
        }
        return result;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//        Product p = list.get(rowIndex);
//        switch(columnIndex){
//            case COLUMN_NAME :
//                p.setName((String)aValue);
//                break;
//            case COLUMN_PRICE :
//                p.setPrice((double)aValue);
//                break;
//            case COLUMN_DATE :
//                result = p.getAddDate();
//                break;
//            default:
//                throw new IllegalArgumentException("Invalid column index");
//        }
    }

    
    
    @Override
    public void addTableModelListener(TableModelListener l) {
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
    }
    
}
