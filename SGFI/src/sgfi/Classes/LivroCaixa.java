/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sgfi.Classes;

import com.mysql.jdbc.PreparedStatement;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author vitiazze
 */

public class LivroCaixa implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer codLanc;
    private Integer codConta;
    private Integer codPessoa;
    private Integer codComunidade;
    private Integer numDoc;
    private String tipoDoc;
    private String historico;
    private Date dataLanc;
    private int anoRef;
    private Double valor;
    private int mesRef;
    private Date dataFinal;

    public Date getDatFinal() {
        return dataFinal;
    }

    public void setDatFinal(Date datFinal) {
        this.dataFinal = datFinal;
    }

    public int getMesRef() {
        return mesRef;
    }

    public void setMesRef(int mesRef) {
        this.mesRef = mesRef;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LivroCaixa() {
    }

    public LivroCaixa(Integer codLanc) {
        this.codLanc = codLanc;
    }

    public Integer getCodLanc() {
        return codLanc;
    }

    public void setCodLanc(Integer codLanc) {
        this.codLanc = codLanc;
    }

    public Integer getCodConta() {
        return codConta;
    }

    public void setCodConta(Integer codConta) {
        this.codConta = codConta;
    }

    public Integer getCodPessoa() {
        return codPessoa;
    }

    public void setCodPessoa(Integer codPessoa) {
        this.codPessoa = codPessoa;
    }

    public Integer getCodComunidade() {
        return codComunidade;
    }

    public void setCodComunidade(Integer codComunidade) {
        this.codComunidade = codComunidade;
    }

    public Integer getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(Integer numDoc) {
        this.numDoc = numDoc;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public Date getDataLanc() {
        return dataLanc;
    }

    public void setDataLanc(Date dataLanc) {
        this.dataLanc = dataLanc;
    }

    public int getAnoRef() {
        return anoRef;
    }

    public void setAnoRef(int anoRef) {
        this.anoRef = anoRef;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codLanc != null ? codLanc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LivroCaixa)) {
            return false;
        }
        LivroCaixa other = (LivroCaixa) object;
        if ((this.codLanc == null && other.codLanc != null) || (this.codLanc != null && !this.codLanc.equals(other.codLanc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sgfi.Classes.LivroCaixa[codLanc=" + codLanc + "]";
    }


     void setCadastrar(ClassConecta conexao){

        try{
        //ClassConecta conexao = new ClassConecta();

        String comando = "INSERT INTO livro_caixa "+
"	(codLanc,  "+
"	codConta,  "+
"	codPessoa,  "+
"	codComunidade,  "+
"	numDoc,  "+
"	tipoDoc,  "+
"	historico,  "+
"	valor,  "+
"	dataLanc "+
"	) "+
"	VALUES "+
"	(null,  "+
"	?,  "+
"	?,  "+
"	?,  "+
"	?,  "+
"	?,  "+
"	?,  "+
"	?,  "+
"	? "+
"	) "
;



        System.out.println("Executando operação...");
        //conexao.conecta();
        PreparedStatement stmt = (PreparedStatement) ClassConecta.con.prepareStatement(comando);

        //Formatar data
        String oDataLanc = null;
        if (getDataLanc() != null){
          SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");
          oDataLanc = formatoData.format(getDataLanc());
        }

        if (getValor() == 0){
            setValor((double)0);
        }

        stmt.setInt(1,getCodConta());
        stmt.setInt(2,getCodPessoa());
        stmt.setInt(3,getCodComunidade());
        stmt.setInt(4,getNumDoc());
        stmt.setString(5,getTipoDoc());
        stmt.setString(6,getHistorico());
        stmt.setDouble(7,getValor());
        stmt.setString(8,oDataLanc);



        stmt.execute();

        System.out.println("Transação Concluída");
        }catch(Exception e){
            System.err.println("Erro na Transação\n"+e);
            JOptionPane.showMessageDialog(null, "Erro na Transação", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
        }
    }

      public ResultSet getGrupoConta(ClassConecta conexao) throws SQLException, ParseException
    {
        ResultSet resultSet = null;

        try
        {
            String comando;
            comando = "SELECT cb.codConta, cb.banco,SUM(lc.valor) AS valor "+
                      " FROM contas cb,livro_caixa lc"+
                      " WHERE cb.codConta = lc.codConta "  ;

            int quantParam = 0;

            if (getCodConta() > 0){
               comando = comando + " AND lc.codConta = ? ";
            }

            if (getMesRef() > 0){
               comando = comando + " AND EXTRACT(YEAR FROM lc.dataLanc) = ? ";
               comando = comando + " AND EXTRACT(MONTH FROM lc.dataLanc) = ? ";
            }else{

            if ( getAnoRef() > 0  ){
               comando = comando + " AND EXTRACT(YEAR FROM lc.datLanc) = ? ";
            }

            }

            comando = comando + " GROUP BY cb.codConta ";
            comando = comando + " order by cb.banco ";
            //ClassConecta conexao = new ClassConecta();
            //conexao.conecta();

            //O parâmetro resultSetType define se o ResultSet irá ser navegável e posicionado ou não:
            //ResultSet.TYPE_FORWARD_ONLY: com este parâmetro o ResultSet não poderá ser navegável, ou seja, poderemos somente avançar no objeto ResultSet para poder buscar valores.
            //ResultSet.TYPE_SCROLL_INSENSITIVE: com este parâmetro o ResultSet poderá ser navegável em qualquer direção, para frente e para trás, e será insensível a mudanças feitas por outras transações ou por outros Statements da mesma transação.
            //ResultSet.TYPE_SCROLL_SENSITIVE: com este parâmetro o ResultSet poderá ser navegável para qualquer direção, e será sensível a mudanças feitas por outras transações ou por outros Statements da mesma transação.
            java.sql.PreparedStatement stmtQuery = ClassConecta.con.prepareStatement(comando);

            if (getCodConta() > 0){
               quantParam = quantParam +1;
               stmtQuery.setInt(quantParam, getCodConta());
            }

            if (getMesRef() > 0){
               quantParam = quantParam +1;
               stmtQuery.setInt(quantParam, getAnoRef());
               quantParam = quantParam +1;
               stmtQuery.setInt(quantParam, getMesRef());
            }else{
            if ( getAnoRef() > 0 ){
               quantParam = quantParam +1;
               stmtQuery.setInt(quantParam, getAnoRef());
            }

            }


        resultSet = stmtQuery.executeQuery();
        resultSet.first();


        }
        catch (SQLException sqlex)
        {
             JOptionPane.showMessageDialog(null,"Não foi Possivél executar o comando sql" + sqlex);

        }

     return resultSet;
     }


       public ResultSet getConsultar() throws SQLException, ParseException
    {
        ResultSet resultSet = null;
        String datIni = null;
        String datFin = null;

        try
        {
            String comando;
            comando = "SELECT lc.*,cb.banco,p.pessoa "+
                      "FROM livro_caixa lc "+
                      "left join contas cb ON (cb.codConta = lc.codConta ) "+
                      "left join pessoas p ON (p.codPessoa = lc.codPessoa) "+
                      "left join comunidade c ON (c.codComunidade = lc.codComunidade) "+
                      "WHERE 1 = 1 ";

            int quantParam = 0;

            if (getCodPessoa() != null){
               comando = comando + " AND lc.codPessoa = ? ";
            }

            if (getCodComunidade() != null){
               comando = comando + " AND lc.codComunidade = ? ";
            }

            if (getCodConta() != null){
               comando = comando + " AND lc.codConta = ? ";
            }

            if ( getDataLanc() != null){
               SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
               datIni = formato.format((Date)getDataLanc());
               datFin = formato.format((Date)getDatFinal());
            }

            if ( getDatFinal() != null){
               comando = comando + " AND lc.dataLanc between ? and ? ";
            }else{
             if ( getDataLanc() != null){
               comando = comando + " AND lc.dataLanc = ? ";
               }
            }

            if (getMesRef() > 0){
               comando = comando + " AND EXTRACT(YEAR FROM lc.dataLanc) = ? ";
               comando = comando + " AND EXTRACT(MONTH FROM lc.dataLanc) = ? ";
            }else{

            if ( getAnoRef() > 0  ){
               comando = comando + " AND EXTRACT(YEAR FROM lc.dataLanc) = ? ";
            }

            }

            comando = comando + " order by codConta ";
            //ClassConecta conexao = new ClassConecta();
            //conexao.conecta();

            //O parâmetro resultSetType define se o ResultSet irá ser navegável e posicionado ou não:
            //ResultSet.TYPE_FORWARD_ONLY: com este parâmetro o ResultSet não poderá ser navegável, ou seja, poderemos somente avançar no objeto ResultSet para poder buscar valores.
            //ResultSet.TYPE_SCROLL_INSENSITIVE: com este parâmetro o ResultSet poderá ser navegável em qualquer direção, para frente e para trás, e será insensível a mudanças feitas por outras transações ou por outros Statements da mesma transação.
            //ResultSet.TYPE_SCROLL_SENSITIVE: com este parâmetro o ResultSet poderá ser navegável para qualquer direção, e será sensível a mudanças feitas por outras transações ou por outros Statements da mesma transação.
            java.sql.PreparedStatement stmtQuery = ClassConecta.con.prepareStatement(comando);

            if (getCodPessoa() != null){
               quantParam = quantParam +1;
               stmtQuery.setInt(quantParam, getCodPessoa());
            }

            if (getCodComunidade() != null){
               quantParam = quantParam +1;
               stmtQuery.setInt(quantParam, getCodComunidade());
            }

            if (getCodConta() != null){
               quantParam = quantParam +1;
               stmtQuery.setInt(quantParam, getCodConta());
            }

            if ( getDatFinal() != null){
               quantParam = quantParam +1;
               stmtQuery.setDate(quantParam, java.sql.Date.valueOf(datIni) );
               quantParam = quantParam +1;
               stmtQuery.setDate(quantParam, java.sql.Date.valueOf(datFin) );
            }else{
             if ( getDataLanc() != null){
               quantParam = quantParam +1;
               stmtQuery.setDate(quantParam, java.sql.Date.valueOf(datIni) );
               }
            }

            if (getMesRef() > 0){
               quantParam = quantParam +1;
               stmtQuery.setInt(quantParam, getAnoRef());
               quantParam = quantParam +1;
               stmtQuery.setInt(quantParam, getMesRef());
            }else{
            if ( getAnoRef() > 0 ){
               quantParam = quantParam +1;
               stmtQuery.setInt(quantParam, getAnoRef());
            }

            }


        resultSet = stmtQuery.executeQuery();


        }
        catch (SQLException sqlex)
        {
             JOptionPane.showMessageDialog(null,"Não foi Possivél executar o comando sql" + sqlex);

        }

     return resultSet;
     }

        public ResultSet getConsultarLancamentos() throws SQLException
    {
        ResultSet resultSet = null;

        try
        {
            String comando;
            comando = "select lc.codLanc,p.pessoa, lc.historico, lc.dataLanc, lc.valor "+
            " from pessoas p, livro_caixa lc "+
            " where p.codPessoa = lc.codPessoa ";

            int quantParam = 0;

            if (getCodPessoa() != null){
               comando = comando + " AND p.codPessoa = ? ";
            }


            comando = comando + " order by dataLanc desc ";
            //O parâmetro resultSetType define se o ResultSet irá ser navegável e posicionado ou não:
            //ResultSet.TYPE_FORWARD_ONLY: com este parâmetro o ResultSet não poderá ser navegável, ou seja, poderemos somente avançar no objeto ResultSet para poder buscar valores.
            //ResultSet.TYPE_SCROLL_INSENSITIVE: com este parâmetro o ResultSet poderá ser navegável em qualquer direção, para frente e para trás, e será insensível a mudanças feitas por outras transações ou por outros Statements da mesma transação.
            //ResultSet.TYPE_SCROLL_SENSITIVE: com este parâmetro o ResultSet poderá ser navegável para qualquer direção, e será sensível a mudanças feitas por outras transações ou por outros Statements da mesma transação.
            java.sql.PreparedStatement stmtQuery = ClassConecta.con.prepareStatement(comando);


            if (getCodPessoa() != null){
               quantParam = quantParam +1;
               stmtQuery.setInt(quantParam, getCodPessoa());

            }

        resultSet = stmtQuery.executeQuery();


        }
        catch (SQLException sqlex)
        {
             JOptionPane.showMessageDialog(null,"Não foi Possivél executar o comando sql" + sqlex);

        }

     return resultSet;
     }

         public double getConsultarSaldo(String valorRetorno,ClassConecta conexao) throws SQLException, ParseException
    {
        ResultSet resultSet = null;
        String datIni = null;
        String datFin = null;
        double  retorno;

        try
        {
            String comando;
            comando =   "SELECT IFNULL(SUM(valor) ,0) AS valorTotal,( "+
                        "SELECT IFNULL(SUM(valor) ,0) "+
                        " FROM livro_caixa lct "+
                        " WHERE lct.dataLanc < lc.dataLanc "+
                        " and lct.codConta = lc.codConta "+
                        " group by lct.codConta "+
                        ") AS valorAnterior "+
                        "  FROM livro_caixa lc  "+
                        "  WHERE lc.codConta = lc.codConta ";

            int quantParam = 0;

            if (getCodPessoa() != null){
               comando = comando + " AND lc.codPessoa = ? ";
            }

            if (getCodConta() != null){
               comando = comando + " AND lc.codConta = ? ";
            }

           if ( getDataLanc() != null){
               SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
               datIni = formato.format((Date)getDataLanc());
               datFin = formato.format((Date)getDatFinal());
           }

            if ( getDatFinal() != null){
               comando = comando + " AND lc.dataLanc between ? and ? ";
            }else{
             if ( getDataLanc() != null){
               comando = comando + " AND lc.dataLanc = ? ";
               }
            }

            if (getMesRef() > 0){
               comando = comando + " AND EXTRACT(YEAR FROM lc.dataLanc) = ? ";
               comando = comando + " AND EXTRACT(MONTH FROM lc.dataLanc) = ? ";
            }else{

            if ( getAnoRef() > 0  ){
               comando = comando + " AND EXTRACT(YEAR FROM lc.dataLanc) = ? ";
            }

            }

             comando = comando + " group by lc.codConta ";
            //ClassConecta conexao = new ClassConecta();

            //conexao.conecta();

            //O parâmetro resultSetType define se o ResultSet irá ser navegável e posicionado ou não:
            //ResultSet.TYPE_FORWARD_ONLY: com este parâmetro o ResultSet não poderá ser navegável, ou seja, poderemos somente avançar no objeto ResultSet para poder buscar valores.
            //ResultSet.TYPE_SCROLL_INSENSITIVE: com este parâmetro o ResultSet poderá ser navegável em qualquer direção, para frente e para trás, e será insensível a mudanças feitas por outras transações ou por outros Statements da mesma transação.
            //ResultSet.TYPE_SCROLL_SENSITIVE: com este parâmetro o ResultSet poderá ser navegável para qualquer direção, e será sensível a mudanças feitas por outras transações ou por outros Statements da mesma transação.
            java.sql.PreparedStatement stmtQuery = ClassConecta.con.prepareStatement(comando);

            if (getCodPessoa() != null){
               quantParam = quantParam +1;
               stmtQuery.setInt(quantParam, getCodPessoa());
            }

            if (getCodConta() != null){
               quantParam = quantParam +1;
               stmtQuery.setInt(quantParam, getCodConta());
            }

            if ( getDatFinal() != null){
               quantParam = quantParam +1;
               stmtQuery.setDate(quantParam, java.sql.Date.valueOf(datIni) );
               quantParam = quantParam +1;
               stmtQuery.setDate(quantParam, java.sql.Date.valueOf(datFin) );
            }else{
             if ( getDataLanc() != null){
               quantParam = quantParam +1;
               stmtQuery.setDate(quantParam, java.sql.Date.valueOf(datIni) );
               }
            }

            if (getMesRef() > 0){
               quantParam = quantParam +1;
               stmtQuery.setInt(quantParam, getAnoRef());
               quantParam = quantParam +1;
               stmtQuery.setInt(quantParam, getMesRef());
            }else{
            if ( getAnoRef() > 0 ){
               quantParam = quantParam +1;
               stmtQuery.setInt(quantParam, getAnoRef());
            }

            }


        resultSet = stmtQuery.executeQuery();
        resultSet.first();

        }
        catch (SQLException sqlex)
        {
             JOptionPane.showMessageDialog(null,"Não foi Possivél executar o comando sql" + sqlex);

        }
     if (valorRetorno.equals("anterior")){
         retorno    = resultSet.getDouble("valorAnterior");
     }else{
         retorno    = resultSet.getDouble("valorTotal");
     }

     return retorno;
     }

    public void setEstornar(ClassConecta conexao){

        try{

        String comando = "INSERT INTO livro_caixa "+
"	(codLanc,  "+
"	codConta,  "+
"	codPessoa,  "+
"	numDoc,  "+
"	tipoDoc,  "+
"	historico,  "+
"	valor,  "+
"	dataLanc "+
"	) "+
" "+
"SELECT NULL AS codLanc,codConta,codPessoa,numDoc,tipoDoc,CONCAT('Estorno de ',historico),(valor*-1),dataLanc "+
"FROM livro_caixa "+
"WHERE codLanc = ? ";



        System.out.println("Executando operação...");
        PreparedStatement stmt = (PreparedStatement) ClassConecta.con.prepareStatement(comando);

        stmt.setInt(1,getCodLanc());
        stmt.execute();

        //System.out.println("Transação Concluída");
        //JOptionPane.showMessageDialog(null, "O REGISTRO foi salvo com sucesso.", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            System.err.println("Erro na Transação\n"+e);
            JOptionPane.showMessageDialog(null, "Erro na Transação", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
        }
    }




}
