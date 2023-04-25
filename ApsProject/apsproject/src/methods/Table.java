package apsproject.src.methods;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import apsproject.src.database.classesdao.RankingDAO;
import apsproject.src.languages.LanguageManager;

public class Table extends JTable {

    private static Table instance;

    private DefaultTableModel model = new DefaultTableModel();
    private LanguageManager languageManager = LanguageManager.getInstance();
    private JTable table;
    private JScrollPane js;

    public Table(){

        //=> Criando a tabela, e definindo a edicao das celulas como false
        table = new JTable(model){
            public boolean isCellEditable(int row, int col) {  
                return false;
            }
        };                                
        
        //=> Definindo as colunas da tabela
        model.addColumn(languageManager.getString("typeTxt"));
        model.addColumn(languageManager.getString("codTxt"));
        model.addColumn(languageManager.getString("playerTxt"));
        model.addColumn(languageManager.getString("equipTxt"));
        model.addColumn(languageManager.getString("lap1Txt"));
        model.addColumn(languageManager.getString("lap2Txt"));
        model.addColumn(languageManager.getString("totalTxt"));

        //=> Definindo o tamanho das minhas colunas 
        table.getColumnModel().getColumn(0).setPreferredWidth(1);
        table.getColumnModel().getColumn(1).setPreferredWidth(6);
        table.getColumnModel().getColumn(2).setPreferredWidth(11);
        table.getColumnModel().getColumn(3).setPreferredWidth(11);
        table.getColumnModel().getColumn(4).setPreferredWidth(10);
        table.getColumnModel().getColumn(5).setPreferredWidth(10);
        table.getColumnModel().getColumn(6).setPreferredWidth(15);
        
        //=> Criando o meu JScrollPane e adicionando a tabela ao mesmo
        js = new JScrollPane(table);
        
        //=> Carrega o modelo da tabela com os dados do DB
        searchData(model);

    }

    //=> Metodo  responsavel por pegar e retornar o meu JScrollPane
    public JScrollPane getJScrollPane() {
        return js;
    }

    //=> Metodo responsavel por atualizar a tabela
    public void updateTableData(){
        model.setNumRows(0);
        searchData(model);
        table.revalidate();
        table.repaint();
    }

    //=> Metodo responsavel por procurar e adicionar os dados que serao postos na tabela
    public static void searchData(DefaultTableModel model) {
        model.setNumRows(0);
        RankingDAO rankDAO = RankingDAO.getInstance();

        //=> Adicionando os dados linha a linha, de acordo com a quantidade de intes no meu array
        for (int i = 0; i < rankDAO.getCod().size(); i++) {
            model.addRow(new Object[]{ rankDAO.getType().get(i),
                                       rankDAO.getCod().get(i), 
                                       rankDAO.getPlayer().get(i),
                                       rankDAO.getEquip().get(i), 
                                       rankDAO.getLap1().get(i),
                                       rankDAO.getLap2().get(i),
                                       rankDAO.getTotal().get(i)});
        }
    }

    //=> Metodo responsaver por retornar uma unica instancia da classe Table para toda a aplicacao.
    public static Table getInstance() {
        if (instance == null) {
            instance = new Table();
        }
        return instance;
    }

}
