package gui;

import dao.ProductDataInterface;
import dao.ProductDatabase;
import dao.ProductStore;
import domain.Product;
import gui.helpers.SimpleListModel;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author dinjo998
 */
public class ProductReport extends javax.swing.JDialog {
	
	private SimpleListModel myModel;
	private final ProductDataInterface dao;
	private SimpleListModel categoryModel;
	
	/**
	 * Creates new form ProductReport
	 * @param parent
	 * @param modal
	 * @param dao
	 */
	public ProductReport(java.awt.Frame parent, boolean modal, ProductDataInterface dao) {
		super(parent, modal);
		this.dao = dao;
		initComponents();
		myModel = new SimpleListModel();
		myModel.updateItems(dao.getProducts());
		productList.setModel(myModel);
		categoryModel = new SimpleListModel();
		categoryModel.updateItems(dao.getCategory());
		cmbCategoryFilter.setModel(categoryModel);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      jScrollPane1 = new javax.swing.JScrollPane();
      productList = new javax.swing.JList<>();
      closeButton = new javax.swing.JButton();
      deleteButton = new javax.swing.JButton();
      editButton = new javax.swing.JButton();
      labelSearchByID = new javax.swing.JLabel();
      txtSearchID = new javax.swing.JTextField();
      searchButton = new javax.swing.JButton();
      categoryFilterLabel = new javax.swing.JLabel();
      cmbCategoryFilter = new javax.swing.JComboBox<>();

      setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

      jScrollPane1.setName("jScrollPane1"); // NOI18N

      productList.setName("productList"); // NOI18N
      jScrollPane1.setViewportView(productList);

      closeButton.setText("Close");
      closeButton.setName("closeButton"); // NOI18N
      closeButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            closeButtonActionPerformed(evt);
         }
      });

      deleteButton.setText("Delete");
      deleteButton.setName("deleteButton"); // NOI18N
      deleteButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            deleteButtonActionPerformed(evt);
         }
      });

      editButton.setText("Edit");
      editButton.setName("editButton"); // NOI18N
      editButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            editButtonActionPerformed(evt);
         }
      });

      labelSearchByID.setText("Search by ID:");
      labelSearchByID.setName("labelSearchByID"); // NOI18N

      txtSearchID.setName("txtSearchID"); // NOI18N

      searchButton.setText("Search");
      searchButton.setName("searchButton"); // NOI18N
      searchButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            searchButtonActionPerformed(evt);
         }
      });

      categoryFilterLabel.setText("Category Filter:");
      categoryFilterLabel.setName("categoryFilterLabel"); // NOI18N

      cmbCategoryFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
      cmbCategoryFilter.setName("cmbCategoryFilter"); // NOI18N
      cmbCategoryFilter.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            cmbCategoryFilterActionPerformed(evt);
         }
      });

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
         .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
         .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
               .addComponent(labelSearchByID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(categoryFilterLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                  .addComponent(txtSearchID)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addComponent(cmbCategoryFilter, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                  .addGap(3, 3, 3)
                  .addComponent(txtSearchID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                  .addComponent(labelSearchByID)
                  .addComponent(searchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(categoryFilterLabel)
               .addComponent(cmbCategoryFilter))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(closeButton)
               .addComponent(deleteButton)
               .addComponent(editButton)))
      );

      pack();
   }// </editor-fold>//GEN-END:initComponents

   private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
      dispose();
   }//GEN-LAST:event_closeButtonActionPerformed

   private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
      Product obj = productList.getSelectedValue();
		if(!productList.isSelectionEmpty()){
			int result = JOptionPane.showConfirmDialog(this, "Are you sure you "
					  + "want to delete " + obj.getName() + "?");
			if(result == JOptionPane.YES_OPTION){
				dao.deleteProduct(obj);
				categoryModel.updateItems(dao.getCategory());
				myModel.updateItems(dao.getProducts());
			}
		}
   }//GEN-LAST:event_deleteButtonActionPerformed

   private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
      Product obj = productList.getSelectedValue();
		if(!productList.isSelectionEmpty()){
			ProductEditor editor = new ProductEditor(this, true, obj, dao);
			editor.setLocationRelativeTo(this);
			editor.setVisible(true);
			myModel.updateItems(dao.getProducts());
		}
   }//GEN-LAST:event_editButtonActionPerformed

   private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
		myModel.updateItems(dao.searchByID(txtSearchID.getText()));
   }//GEN-LAST:event_searchButtonActionPerformed

   private void cmbCategoryFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCategoryFilterActionPerformed
      myModel.updateItems(dao.filterByCategory((String) cmbCategoryFilter.getSelectedItem()));
   }//GEN-LAST:event_cmbCategoryFilterActionPerformed


   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JLabel categoryFilterLabel;
   private javax.swing.JButton closeButton;
   private javax.swing.JComboBox<String> cmbCategoryFilter;
   private javax.swing.JButton deleteButton;
   private javax.swing.JButton editButton;
   private javax.swing.JScrollPane jScrollPane1;
   private javax.swing.JLabel labelSearchByID;
   private javax.swing.JList<Product> productList;
   private javax.swing.JButton searchButton;
   private javax.swing.JTextField txtSearchID;
   // End of variables declaration//GEN-END:variables
}