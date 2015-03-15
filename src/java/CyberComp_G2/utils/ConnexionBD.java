package CyberComp_G2.utils;

import com.sun.rowset.CachedRowSetImpl;

import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Gère les interactions avec la base de données
 * @author oprisora
 */
public enum ConnexionBD {
    INSTANCE;

    private DataSource dataSource;

    /**
     * Initialise le DataSource à utiliser
     * @param dataSource : le DataSource
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Exécute une requête (SELECT) en base de données
     * @param requete la requête à exécuter
     * @param params les différents champs de la requête
     * @return le résultat de la requête
     * @throws SQLException lors d'un problème avec la base de données
     */
    public CachedRowSet executeRequete(String requete, Object... params) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(String.format(requete, params));
        ResultSet resultSet = preparedStatement.executeQuery();

        CachedRowSet rowSet = new CachedRowSetImpl();
        rowSet.populate(resultSet);

        connection.close();

        return rowSet;
    }

    /**
     * Execute un UPDATE, INSERT ou DELETE
     * @param miseAJour : la mise à jour à exécuter
     * @param params : les champs de la mise à jour
     * @throws SQLException lors d'un problème avec la base de données
     */
    public void executeMiseAJour(String miseAJour, String... params) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(String.format(miseAJour, params));
        preparedStatement.executeUpdate();
        connection.close();
    }
}
