package gr.aueb.cf.schoolapp;

import gr.aueb.cf.schoolapp.view_controller.*;

import java.awt.*;

public class Main {
    private static final LandingPage landingPage = new LandingPage();
    private static final LoginPage loginPage = new LoginPage();
    private static final Dashboard dashboard = new Dashboard();
    private static final InsertProfPage insertProfPage = new InsertProfPage();
    private static final InsertProfSuccessPage insertProfSuccessPage = new InsertProfSuccessPage();
    private static final ViewProfsPage viewProfsPage = new ViewProfsPage();
    private static final ViewProfDetailPage viewProfDetailPage = new ViewProfDetailPage();
    private static final UpdateProfPage updateProfPage = new UpdateProfPage();
    private static final UpdateProfSuccessPage updateProfSuccessPage = new UpdateProfSuccessPage();
    private static final InsertStudPage insertStudPage = new InsertStudPage();
    private static final InsertStudSuccessPage insertStudSuccessPage = new InsertStudSuccessPage();
    private static final ViewStudsPage viewStudsPage = new ViewStudsPage();
    private static final ViewStudDetailPage viewStudDetailPage = new ViewStudDetailPage();
    private static final UpdateStudPage updateStudPage = new UpdateStudPage();
    private static final UpdateStudSuccessPage updateStudSuccessPage = new UpdateStudSuccessPage();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            landingPage.setVisible(true);
            landingPage.setLocationRelativeTo(null);
            loginPage.setVisible(false);
            loginPage.setLocationRelativeTo(null);
            dashboard.setVisible(false);
            dashboard.setLocationRelativeTo(null);
            insertProfPage.setVisible(false);
            insertProfPage.setLocationRelativeTo(null);
            insertProfSuccessPage.setVisible(false);
            insertProfSuccessPage.setLocationRelativeTo(null);
            viewProfsPage.setVisible(false);
            viewProfsPage.setLocationRelativeTo(null);
            viewProfDetailPage.setVisible(false);
            viewProfDetailPage.setLocationRelativeTo(null);
            updateProfPage.setVisible(false);
            updateProfPage.setLocationRelativeTo(null);
            updateProfSuccessPage.setVisible(false);
            updateProfSuccessPage.setLocationRelativeTo(null);
            insertStudPage.setVisible(false);
            insertStudPage.setLocationRelativeTo(null);
            insertStudSuccessPage.setVisible(false);
            insertStudSuccessPage.setLocationRelativeTo(null);
            viewStudsPage.setVisible(false);
            viewStudsPage.setLocationRelativeTo(null);
            viewStudDetailPage.setVisible(false);
            viewStudDetailPage.setLocationRelativeTo(null);
            updateStudPage.setVisible(false);
            updateStudPage.setLocationRelativeTo(null);
            updateStudSuccessPage.setVisible(false);
            updateStudSuccessPage.setLocationRelativeTo(null);
        });
    }

    public static LandingPage getLandingPage() {
        return landingPage;
    }

    public static LoginPage getLoginPage() {
        return loginPage;
    }

    public static Dashboard getDashboard() {
        return dashboard;
    }

    public static InsertProfPage getInsertProfPage() {
        return insertProfPage;
    }

    public static InsertProfSuccessPage getInsertProfSuccessPage() {
        return insertProfSuccessPage;
    }

    public static ViewProfsPage getViewProfsPage() {
        return viewProfsPage;
    }

    public static ViewProfDetailPage getViewProfDetailPage() {
        return viewProfDetailPage;
    }

    public static UpdateProfPage getUpdateProfPage() {
        return updateProfPage;
    }

    public static UpdateProfSuccessPage getUpdateProfSuccessPage() {
        return updateProfSuccessPage;
    }


    public static InsertStudPage getInsertStudPage() {
        return insertStudPage;
    }

    public static InsertStudSuccessPage getInsertStudSuccessPage() {
        return insertStudSuccessPage;
    }

    public static ViewStudsPage getViewStudsPage() {
        return viewStudsPage;
    }

    public static ViewStudDetailPage getViewStudDetailPage() {
        return viewStudDetailPage;
    }


    public static UpdateStudPage getUpdateStudPage() {
        return updateStudPage;
    }

    public static UpdateStudSuccessPage getUpdateStudSuccessPage() {
        return updateStudSuccessPage;
    }

}
