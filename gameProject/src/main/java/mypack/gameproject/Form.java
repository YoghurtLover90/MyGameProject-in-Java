/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package mypack.gameproject;

import java.awt.Button;
import java.awt.Color;
import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class Form extends javax.swing.JFrame {
    
    public Form() {
        initComponents();
        jButton5.hide();
    }
    
    int tourCount;
    
    //Basic Stats
    int popNum=3;
    int popKNumber=3000;
    int acres=1;
    int gold=1000;
    int provinces=5;
    int manpower=300;

    //Variable Stats
    int growth=3;
    int income=300;
    int incomeRate=0;

    //Commoner Stats
    int foodPerPop=1;
    int popTaxRate=0;
    int volunteerConscription=0;

    //Army
    int armySize=0;
    int armyQuality=2;
    int armyPayments=10;
    int conscriptionRate=10;
    int conscriptTax=0;

    //Nobles
    int nobleSoldierRate=6;
    int nobleTaxRate=0;

    //bourgeoisie
    int bourgeoisieGrowthContribution=0;
    int bourgeoisieTaxRate=0;
    
    //extra
    int totalArmySize=armySize+(nobleSoldierRate*(popKNumber/100));
    int totalConscriptionRate=6;
    int totalGrowth=3;
    boolean war=false;
    boolean victory=false;
    boolean truce=true;
    int tempTourCount=0;
    int totalPower;
    int KKtotalPower;
    
    //kk variables
    int KKprovinces=5; 
    int KKarmySize=300;
    int KKarmyQuality=4;
    Random KKarmyRandom = new Random();
    Random KKattackRandom=new Random();
    int KKarmySizeIncrease=0;
    int KKattackCahnce;
    void KKarmyAdder(){
        KKarmySizeIncrease=300+(10*KKarmyRandom.nextInt(10));
        KKarmySize+=KKarmySizeIncrease;
        KKArmySize_Label.setText(Integer.toString(KKarmySize));
    }
    void KKattackChanceEvent(){
        KKattackCahnce=KKattackRandom.nextInt(5);
        System.out.println(Integer.toString(KKattackCahnce));
        if(KKattackCahnce==2 && tourCount>5 && truce==false){
            war=true;
            WarEvent();
        }
    }
    
    void WarEvent(){
        if(war==true){
            jButton1.hide();
            jButton2.hide();
            jButton3.hide();
            jButton4.hide();
            Tur_Button.hide();
            Button_Soylular.hide();
            JOptionPane.showMessageDialog(null,"Bir muharebe başlıyor!","Bilgi Mesajı",JOptionPane.INFORMATION_MESSAGE);
            BattleArmySize_Label.setText(Integer.toString(totalArmySize));
            KKBattleArmySize_Label.setText(Integer.toString(KKarmySize));
            jButton5.show();
        }
        else{
            truce=true;
            tempTourCount=5;
            totalPower=0;
            KKtotalPower=0;
            totalCasulties=0;
            KKtotalCasulties=0;
            jButton1.show();
            jButton2.show();
            jButton3.show();
            jButton4.show();
            Tur_Button.show();
            Button_Soylular.show();
            SoldierChange();
            if(victory==true){
                JOptionPane.showMessageDialog(null,"Bir muharebe kazanıldı!","Bilgi Mesajı",JOptionPane.INFORMATION_MESSAGE);
                KKprovinces--;
                provinces++;
                ProvinceChange();
                KKProvinces_Label.setText(Integer.toString(KKprovinces));
            }
            else{
                JOptionPane.showMessageDialog(null,"Bir muharebe kaybedildi...","Bilgi Mesajı",JOptionPane.INFORMATION_MESSAGE);
                provinces--;
                KKprovinces++;
                KKProvinceChange();
                Label_Provinces.setText(Integer.toString(provinces));
            }
            BattleArmySize_Label.setText("-");
            KKBattleArmySize_Label.setText("-");
            CasualitySize_Label.setText("-");
            KKCasualitySize_Label.setText("-");
            ActiveForces_Label.setText("-");
            KKActiveForces_Label.setText("-");
            Casualities_Label.setText("-");
            KKCasualities_Label.setText("-");
            jButton5.hide();
        }
    }
    
    void PopChange(){
        Label_PopNum.setText(Integer.toString(popNum)+"k");
    }
    
    void GoldChange(){
        Label_Gold.setText(Integer.toString(gold));
        if(gold<0){
            armySize-=(armySize*10)/100;
        }
    }
    
    void FPPChange(){
        Label_FPP.setText(Integer.toString(foodPerPop));
    }
    
    void ManpowerChange(){
        totalConscriptionRate=volunteerConscription+conscriptionRate;
        manpower=(popKNumber/100)*totalConscriptionRate;
        if(manpower<0){
            manpower=0;
        }
        Label_Manpower.setText(Integer.toString(manpower));
    }
    
    void ProvinceChange(){
        Label_Provinces.setText(Integer.toString(provinces));
    }
    
    void KKProvinceChange(){
        KKProvinces_Label.setText(Integer.toString(KKprovinces));
    }
    
    void GrowthChange(){
        totalGrowth=growth+bourgeoisieGrowthContribution;
        Label_Growth.setText(Integer.toString(totalGrowth));
    }
    
    void SoldierChange(){
        if(armySize<0){
            armySize=0;
        }
        totalArmySize=armySize+(nobleSoldierRate*(popKNumber/100));
        Label_Soldiers.setText(Integer.toString(totalArmySize));
    }
    
    void AcreChange(){
        Label_Acre.setText(Integer.toString(acres));
    }
    
    
    void ArmyQualityChane(){
        Label_ArmyQuality.setText(Integer.toString(armyQuality));
    }
    
    void IncomeChange(){
        incomeRate=popTaxRate+nobleTaxRate+bourgeoisieTaxRate+conscriptTax;
        income=((popNum*50)+(popNum*incomeRate))-((armySize/50)*armyPayments);
        Label_TurGeliri.setText(Integer.toString(income));
    }
    
    void FFPCalculator(){
        
        
        foodPerPop=(3*acres)/popNum;
        if (foodPerPop < 1) {
            growth = -2;
        } 
        else if (foodPerPop < 2 && foodPerPop >= 1) {
            growth = 3;
        } 
        else if (foodPerPop < 3 && foodPerPop >= 2) {
            growth = 6;
        } 
        else if (foodPerPop < 4 && foodPerPop >= 3) {
            growth = 9;
        }
        
        FPPChange();
        GrowthChange();
    }
    
    void ManpowerChangeChecker(){
        if(manpower<armySize){
            armySize-=(armySize-manpower);
        }
        SoldierChange();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Button_Soylular = new javax.swing.JButton();
        Button_Burjuvazi = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Label_Acre = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        Label_Growth = new javax.swing.JLabel();
        Label_Gold = new javax.swing.JLabel();
        Label_Soldiers = new javax.swing.JLabel();
        Label_FPP = new javax.swing.JLabel();
        Label_PopNum = new javax.swing.JLabel();
        Label_Manpower = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        Button_HalkVergiler = new javax.swing.JButton();
        Button_AkreAl = new javax.swing.JButton();
        Label_Content = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        Button_AskerEq = new javax.swing.JButton();
        Button_Conscription = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        Label_ArmyQuality = new javax.swing.JLabel();
        Button_Conscription1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        Button_Soylular1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        Button_Soylular2 = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        jToggleButton4 = new javax.swing.JToggleButton();
        jToggleButton5 = new javax.swing.JToggleButton();
        jToggleButton6 = new javax.swing.JToggleButton();
        jToggleButton7 = new javax.swing.JToggleButton();
        jLabel55 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jToggleButton9 = new javax.swing.JToggleButton();
        jToggleButton10 = new javax.swing.JToggleButton();
        jToggleButton11 = new javax.swing.JToggleButton();
        jToggleButton12 = new javax.swing.JToggleButton();
        jToggleButton13 = new javax.swing.JToggleButton();
        jPanel8 = new javax.swing.JPanel();
        jToggleButton19 = new javax.swing.JToggleButton();
        jLabel63 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jToggleButton18 = new javax.swing.JToggleButton();
        jToggleButton17 = new javax.swing.JToggleButton();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jToggleButton16 = new javax.swing.JToggleButton();
        jToggleButton15 = new javax.swing.JToggleButton();
        jLabel62 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jToggleButton22 = new javax.swing.JToggleButton();
        jToggleButton24 = new javax.swing.JToggleButton();
        jToggleButton21 = new javax.swing.JToggleButton();
        jToggleButton20 = new javax.swing.JToggleButton();
        jLabel73 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jToggleButton23 = new javax.swing.JToggleButton();
        jLabel78 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jToggleButton27 = new javax.swing.JToggleButton();
        jLabel87 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jToggleButton29 = new javax.swing.JToggleButton();
        jToggleButton26 = new javax.swing.JToggleButton();
        jLabel82 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jToggleButton31 = new javax.swing.JToggleButton();
        jLabel90 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jToggleButton28 = new javax.swing.JToggleButton();
        jLabel86 = new javax.swing.JLabel();
        jToggleButton32 = new javax.swing.JToggleButton();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jToggleButton33 = new javax.swing.JToggleButton();
        jLabel95 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel96 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel97 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        Button_Halk = new javax.swing.JButton();
        Button_Ordu = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        Label_Provinces = new javax.swing.JLabel();
        Tur_Button = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        Label_TurGeliri = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        Label_TourCount = new javax.swing.JLabel();
        Savas_Button1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        KKProvinces_Label = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        KKArmySize_Label = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        CasualitySize_Label = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        KKActiveForces_Label = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel99 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        KKCasualities_Label = new javax.swing.JLabel();
        ActiveForces_Label = new javax.swing.JLabel();
        Casualities_Label = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel79 = new javax.swing.JLabel();
        KKCasualitySize_Label = new javax.swing.JLabel();
        BattleArmySize_Label = new javax.swing.JLabel();
        KKBattleArmySize_Label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));
        setLocation(new java.awt.Point(0, 0));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("sansserif", 0, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Muharebe Sahası");
        jLabel2.setToolTipText("");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(400, 480, 500, 40);

        jLabel3.setFont(new java.awt.Font("sansserif", 0, 36)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Politik Aksiyonlar");
        jLabel3.setToolTipText("");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(400, 0, 500, 47);

        Button_Soylular.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        Button_Soylular.setText("Soylular");
        Button_Soylular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_SoylularActionPerformed(evt);
            }
        });
        getContentPane().add(Button_Soylular);
        Button_Soylular.setBounds(660, 60, 110, 40);

        Button_Burjuvazi.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        Button_Burjuvazi.setText("Burjuvazi");
        Button_Burjuvazi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_BurjuvaziActionPerformed(evt);
            }
        });
        getContentPane().add(Button_Burjuvazi);
        Button_Burjuvazi.setBounds(780, 60, 110, 40);

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("KK Vilayet Sayısı: ");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(150, 620, 200, 32);

        jLabel5.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Pop Sayısı:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(0, 60, 130, 32);

        Label_Acre.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        Label_Acre.setForeground(new java.awt.Color(255, 255, 255));
        Label_Acre.setText("1");
        getContentPane().add(Label_Acre);
        Label_Acre.setBounds(340, 180, 60, 30);

        jLabel10.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("AAP Sayısı:");
        jLabel10.setToolTipText("Azami Askeri Personel Sayısı (Askere alınabilecek azami kişi sayısını gösterir)");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(0, 140, 130, 32);

        jLabel11.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Büyüme:");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(240, 100, 100, 32);

        jLabel6.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("KBDY:");
        jLabel6.setToolTipText("Kişi Başına Düşen Yemek");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(260, 140, 72, 32);

        jLabel12.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Akre:");
        getContentPane().add(jLabel12);
        jLabel12.setBounds(280, 180, 60, 32);

        Label_Growth.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        Label_Growth.setForeground(new java.awt.Color(255, 255, 255));
        Label_Growth.setText("3");
        getContentPane().add(Label_Growth);
        Label_Growth.setBounds(340, 100, 30, 30);

        Label_Gold.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        Label_Gold.setForeground(new java.awt.Color(255, 255, 255));
        Label_Gold.setText("1000");
        getContentPane().add(Label_Gold);
        Label_Gold.setBounds(60, 100, 100, 32);

        Label_Soldiers.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        Label_Soldiers.setForeground(new java.awt.Color(255, 255, 255));
        Label_Soldiers.setText("180");
        getContentPane().add(Label_Soldiers);
        Label_Soldiers.setBounds(150, 180, 100, 30);

        Label_FPP.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        Label_FPP.setForeground(new java.awt.Color(255, 255, 255));
        Label_FPP.setText("1");
        getContentPane().add(Label_FPP);
        Label_FPP.setBounds(340, 140, 50, 30);

        Label_PopNum.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        Label_PopNum.setForeground(new java.awt.Color(255, 255, 255));
        Label_PopNum.setText("3k");
        getContentPane().add(Label_PopNum);
        Label_PopNum.setBounds(130, 60, 100, 30);

        Label_Manpower.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        Label_Manpower.setForeground(new java.awt.Color(255, 255, 255));
        Label_Manpower.setText("300");
        getContentPane().add(Label_Manpower);
        Label_Manpower.setBounds(130, 140, 110, 30);

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.RIGHT);

        Button_HalkVergiler.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        Button_HalkVergiler.setText("Vergiler");
        Button_HalkVergiler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_HalkVergilerActionPerformed(evt);
            }
        });

        Button_AkreAl.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        Button_AkreAl.setText("Akre Al");
        Button_AkreAl.setToolTipText("300 Altın karşılığı bir akre tarla satın alınır");
        Button_AkreAl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_AkreAlActionPerformed(evt);
            }
        });

        Label_Content.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        Label_Content.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(Button_AkreAl, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(Button_HalkVergiler, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Label_Content, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(96, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Button_HalkVergiler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Label_Content, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Button_AkreAl, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab2", jPanel2);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Button_AskerEq.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        Button_AskerEq.setText("Ekipman Harcamaları");
        Button_AskerEq.setToolTipText("50 asker başına düşen ekipman harcamalarını ayarlar");
        Button_AskerEq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_AskerEqActionPerformed(evt);
            }
        });
        jPanel3.add(Button_AskerEq, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 40));

        Button_Conscription.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        Button_Conscription.setText("Askere Alım");
        Button_Conscription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_ConscriptionActionPerformed(evt);
            }
        });
        jPanel3.add(Button_Conscription, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 140, 40));

        jLabel14.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Ordu Kalitesi:");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, -1, -1));

        Label_ArmyQuality.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        Label_ArmyQuality.setForeground(new java.awt.Color(102, 204, 0));
        Label_ArmyQuality.setText("2");
        jPanel3.add(Label_ArmyQuality, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 48, -1));

        Button_Conscription1.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        Button_Conscription1.setText("Asker Al");
        Button_Conscription1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_Conscription1ActionPerformed(evt);
            }
        });
        jPanel3.add(Button_Conscription1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 140, 40));

        jTabbedPane1.addTab("tab3", jPanel3);

        Button_Soylular1.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        Button_Soylular1.setText("Soylu Vergileri");
        Button_Soylular1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_Soylular1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(Button_Soylular1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(295, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(Button_Soylular1, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                .addGap(26, 26, 26))
        );

        jTabbedPane1.addTab("tab4", jPanel4);

        Button_Soylular2.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        Button_Soylular2.setText("Burjuvazi Vergileri");
        Button_Soylular2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_Soylular2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(Button_Soylular2)
                .addContainerGap(253, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(Button_Soylular2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        jTabbedPane1.addTab("tab1", jPanel1);

        getContentPane().add(jTabbedPane1);
        jTabbedPane1.setBounds(410, 120, 620, 120);

        jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.RIGHT);

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel33.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(204, 0, 0));
        jLabel33.setText("-50 Vergi");
        jPanel6.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 76, -1));

        jLabel34.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(51, 204, 0));
        jLabel34.setText("+%10 Askere Katılım");
        jPanel6.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, -1, -1));

        jLabel32.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(204, 0, 0));
        jLabel32.setText("-25 Vergi");
        jPanel6.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 76, -1));

        jLabel35.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jPanel6.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 36, 100, -1));

        jLabel31.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(204, 0, 0));
        jLabel31.setText("-10 Vergi");
        jPanel6.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 76, -1));

        jLabel36.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(51, 204, 0));
        jLabel36.setText("+%3 Askere Katılım");
        jPanel6.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, 150, -1));

        jLabel30.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(51, 204, 0));
        jLabel30.setText("+10 Vergi");
        jPanel6.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 76, -1));

        jLabel37.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(204, 0, 0));
        jLabel37.setText("-%3 Askere Katılım");
        jPanel6.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, 150, -1));

        jLabel38.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(204, 0, 0));
        jLabel38.setText("-%6 Askere Katılım");
        jPanel6.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, 150, -1));

        jLabel29.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 204, 0));
        jLabel29.setText("+25 Vergi");
        jPanel6.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 76, -1));

        jLabel28.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(51, 204, 0));
        jLabel28.setText("+50 Vergi");
        jPanel6.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, 76, -1));

        jLabel39.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(204, 0, 0));
        jLabel39.setText("-%10 Askere Katılım");
        jPanel6.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 200, 150, -1));

        jLabel40.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel40.setText("0 Vergi");
        jPanel6.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 76, -1));

        jLabel42.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(51, 204, 0));
        jLabel42.setText("+%6 Askere Katılım");
        jPanel6.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 150, -1));

        buttonGroup1.add(jToggleButton1);
        jToggleButton1.setText("Uygula");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        jPanel6.add(jToggleButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 6, 111, -1));

        buttonGroup1.add(jToggleButton2);
        jToggleButton2.setText("Uygula");
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });
        jPanel6.add(jToggleButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 36, 111, -1));

        buttonGroup1.add(jToggleButton3);
        jToggleButton3.setText("Uygula");
        jToggleButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton3ActionPerformed(evt);
            }
        });
        jPanel6.add(jToggleButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 70, 111, -1));

        buttonGroup1.add(jToggleButton4);
        jToggleButton4.setSelected(true);
        jToggleButton4.setText("Uygula");
        jToggleButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton4ActionPerformed(evt);
            }
        });
        jPanel6.add(jToggleButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 104, 111, -1));

        buttonGroup1.add(jToggleButton5);
        jToggleButton5.setText("Uygula");
        jToggleButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton5ActionPerformed(evt);
            }
        });
        jPanel6.add(jToggleButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 138, 111, -1));

        buttonGroup1.add(jToggleButton6);
        jToggleButton6.setText("Uygula");
        jToggleButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton6ActionPerformed(evt);
            }
        });
        jPanel6.add(jToggleButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 172, 111, -1));

        buttonGroup1.add(jToggleButton7);
        jToggleButton7.setText("Uygula");
        jToggleButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton7ActionPerformed(evt);
            }
        });
        jPanel6.add(jToggleButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 202, 111, -1));

        jLabel55.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel55.setText("0 Hoşnutluk");
        jPanel6.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, 150, -1));

        jTabbedPane2.addTab("tab2", jPanel6);

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel45.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel45.setText("10 Altın");
        jPanel7.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 76, -1));

        jLabel46.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(51, 204, 0));
        jLabel46.setText("+2 Ordu Kalitesi");
        jPanel7.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 120, -1));

        jLabel48.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel48.setText("20 Altın");
        jPanel7.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 76, -1));

        jLabel47.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(51, 204, 0));
        jLabel47.setText("+4 Ordu Kalitesi");
        jPanel7.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, 120, -1));

        jLabel49.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel49.setText("30 Altın");
        jPanel7.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 76, -1));

        jLabel50.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(51, 204, 0));
        jLabel50.setText("+6 Ordu Kalitesi");
        jPanel7.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 120, -1));

        jLabel52.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel52.setText("40 Altın");
        jPanel7.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 76, -1));

        jLabel51.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(51, 204, 0));
        jLabel51.setText("+8 Ordu Kalitesi");
        jPanel7.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, 120, -1));

        jLabel53.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel53.setText("50 Altın");
        jPanel7.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 76, -1));

        jLabel54.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(51, 204, 0));
        jLabel54.setText("+10 Ordu Kalitesi");
        jPanel7.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 160, 130, -1));

        buttonGroup2.add(jToggleButton9);
        jToggleButton9.setSelected(true);
        jToggleButton9.setText("Uygula");
        jToggleButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton9ActionPerformed(evt);
            }
        });
        jPanel7.add(jToggleButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, -1));

        buttonGroup2.add(jToggleButton10);
        jToggleButton10.setText("Uygula");
        jToggleButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton10ActionPerformed(evt);
            }
        });
        jPanel7.add(jToggleButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, -1, -1));

        buttonGroup2.add(jToggleButton11);
        jToggleButton11.setText("Uygula");
        jToggleButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton11ActionPerformed(evt);
            }
        });
        jPanel7.add(jToggleButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, -1, -1));

        buttonGroup2.add(jToggleButton12);
        jToggleButton12.setText("Uygula");
        jToggleButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton12ActionPerformed(evt);
            }
        });
        jPanel7.add(jToggleButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, -1, -1));

        buttonGroup2.add(jToggleButton13);
        jToggleButton13.setText("Uygula");
        jToggleButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton13ActionPerformed(evt);
            }
        });
        jPanel7.add(jToggleButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

        jTabbedPane2.addTab("tab3", jPanel7);

        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonGroup3.add(jToggleButton19);
        jToggleButton19.setText("Uygula");
        jToggleButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton19ActionPerformed(evt);
            }
        });
        jPanel8.add(jToggleButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 70, -1));

        jLabel63.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel63.setText("%3 Askere Alım");
        jPanel8.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 120, -1));

        jLabel68.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(102, 204, 0));
        jLabel68.setText("+20 Vergi");
        jPanel8.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 76, -1));

        jLabel67.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(102, 204, 0));
        jLabel67.setText("+10 Vergi");
        jPanel8.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, 76, -1));

        jLabel64.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel64.setText("%5 Askere Alım");
        jPanel8.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 130, -1));

        buttonGroup3.add(jToggleButton18);
        jToggleButton18.setText("Uygula");
        jToggleButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton18ActionPerformed(evt);
            }
        });
        jPanel8.add(jToggleButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 70, -1));

        buttonGroup3.add(jToggleButton17);
        jToggleButton17.setSelected(true);
        jToggleButton17.setText("Uygula");
        jToggleButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton17ActionPerformed(evt);
            }
        });
        jPanel8.add(jToggleButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 70, -1));

        jLabel65.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel65.setText("%10 Askere Alım");
        jPanel8.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 130, -1));

        jLabel66.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel66.setText("0 Vergi");
        jPanel8.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, 76, -1));

        jLabel60.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(204, 0, 51));
        jLabel60.setText("-10 Vergi");
        jPanel8.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 120, 76, -1));

        jLabel61.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel61.setText("%15 Askere Alım");
        jPanel8.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 130, -1));

        buttonGroup3.add(jToggleButton16);
        jToggleButton16.setText("Uygula");
        jToggleButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton16ActionPerformed(evt);
            }
        });
        jPanel8.add(jToggleButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 70, -1));

        buttonGroup3.add(jToggleButton15);
        jToggleButton15.setText("Uygula");
        jToggleButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton15ActionPerformed(evt);
            }
        });
        jPanel8.add(jToggleButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 70, -1));

        jLabel62.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel62.setText("%20 Askere Alım");
        jPanel8.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 130, -1));

        jLabel59.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(204, 0, 51));
        jLabel59.setText("-20 Vergi");
        jPanel8.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 150, 76, -1));

        jTabbedPane2.addTab("tab4", jPanel8);

        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonGroup4.add(jToggleButton22);
        jToggleButton22.setSelected(true);
        jToggleButton22.setText("Uygula");
        jToggleButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton22ActionPerformed(evt);
            }
        });
        jPanel9.add(jToggleButton22, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 70, -1));

        buttonGroup4.add(jToggleButton24);
        jToggleButton24.setText("Uygula");
        jToggleButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton24ActionPerformed(evt);
            }
        });
        jPanel9.add(jToggleButton24, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 70, -1));

        buttonGroup4.add(jToggleButton21);
        jToggleButton21.setText("Uygula");
        jToggleButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton21ActionPerformed(evt);
            }
        });
        jPanel9.add(jToggleButton21, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 70, -1));

        buttonGroup4.add(jToggleButton20);
        jToggleButton20.setText("Uygula");
        jToggleButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton20ActionPerformed(evt);
            }
        });
        jPanel9.add(jToggleButton20, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 70, -1));

        jLabel73.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel73.setText("%6 Asker Katkısı");
        jPanel9.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 140, -1));

        jLabel71.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(51, 204, 0));
        jLabel71.setText("+10 Vergi");
        jPanel9.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, 76, -1));

        jLabel77.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel77.setText("%10 Asker Katkısı");
        jPanel9.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, 140, -1));

        jLabel70.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(51, 204, 0));
        jLabel70.setText("+20 Vergi");
        jPanel9.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, 76, -1));

        jLabel74.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel74.setText("0 Vergi");
        jPanel9.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, 76, -1));

        buttonGroup4.add(jToggleButton23);
        jToggleButton23.setText("Uygula");
        jToggleButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton23ActionPerformed(evt);
            }
        });
        jPanel9.add(jToggleButton23, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 70, -1));

        jLabel78.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(204, 0, 51));
        jLabel78.setText("-20 Vergi");
        jPanel9.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, 76, -1));

        jLabel72.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel72.setText("%4 Asker Katkısı");
        jPanel9.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 140, -1));

        jLabel75.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(204, 0, 51));
        jLabel75.setText("-10 Vergi");
        jPanel9.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 130, 76, -1));

        jLabel76.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel76.setText("%8 Asker Katkısı");
        jPanel9.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 140, -1));

        jLabel69.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel69.setText("%2 Asker Katkısı");
        jPanel9.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 140, -1));

        jTabbedPane2.addTab("tab5", jPanel9);

        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonGroup5.add(jToggleButton27);
        jToggleButton27.setText("Uygula");
        jToggleButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton27ActionPerformed(evt);
            }
        });
        jPanel10.add(jToggleButton27, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 111, -1));

        jLabel87.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(204, 0, 51));
        jLabel87.setText("-2 Büyüme");
        jPanel10.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 90, -1));

        jLabel83.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(102, 204, 0));
        jLabel83.setText("+1 Büyüme");
        jPanel10.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 90, -1));

        jLabel91.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(102, 204, 0));
        jLabel91.setText("+10 Vergi");
        jPanel10.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 76, -1));

        buttonGroup5.add(jToggleButton29);
        jToggleButton29.setText("Uygula");
        jToggleButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton29ActionPerformed(evt);
            }
        });
        jPanel10.add(jToggleButton29, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 111, -1));

        buttonGroup5.add(jToggleButton26);
        jToggleButton26.setSelected(true);
        jToggleButton26.setText("Uygula");
        jToggleButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton26ActionPerformed(evt);
            }
        });
        jPanel10.add(jToggleButton26, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 111, -1));

        jLabel82.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel82.setText("0 Vergi");
        jPanel10.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 76, -1));

        jLabel84.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(102, 204, 0));
        jLabel84.setText("+20 Vergi");
        jPanel10.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 76, -1));

        jLabel92.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(204, 0, 51));
        jLabel92.setText("-20 Vergi");
        jPanel10.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 76, -1));

        buttonGroup5.add(jToggleButton31);
        jToggleButton31.setText("Uygula");
        jToggleButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton31ActionPerformed(evt);
            }
        });
        jPanel10.add(jToggleButton31, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 111, -1));

        jLabel90.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(204, 0, 51));
        jLabel90.setText("-1 Büyüme");
        jPanel10.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, 90, -1));

        jLabel85.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(102, 204, 0));
        jLabel85.setText("+2 Büyüme");
        jPanel10.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 90, -1));

        jLabel89.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(204, 0, 51));
        jLabel89.setText("-10 Vergi");
        jPanel10.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 76, -1));

        buttonGroup5.add(jToggleButton28);
        jToggleButton28.setText("Uygula");
        jToggleButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton28ActionPerformed(evt);
            }
        });
        jPanel10.add(jToggleButton28, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 111, -1));

        jLabel86.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel86.setText("0 Büyüme");
        jPanel10.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 90, -1));

        buttonGroup5.add(jToggleButton32);
        jToggleButton32.setText("Uygula");
        jToggleButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton32ActionPerformed(evt);
            }
        });
        jPanel10.add(jToggleButton32, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 111, -1));

        jLabel93.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(204, 0, 51));
        jLabel93.setText("-30 Vergi");
        jPanel10.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 76, -1));

        jLabel94.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel94.setForeground(new java.awt.Color(102, 204, 0));
        jLabel94.setText("+3 Büyüme");
        jPanel10.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 90, -1));

        buttonGroup5.add(jToggleButton33);
        jToggleButton33.setText("Uygula");
        jToggleButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton33ActionPerformed(evt);
            }
        });
        jPanel10.add(jToggleButton33, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 111, -1));

        jLabel95.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(102, 204, 0));
        jLabel95.setText("+30 Vergi");
        jPanel10.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 76, -1));

        jLabel98.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(204, 0, 51));
        jLabel98.setText("-3 Büyüme");
        jPanel10.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 190, 90, -1));

        jTabbedPane2.addTab("tab6", jPanel10);

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel96.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel96.setForeground(new java.awt.Color(204, 0, 51));
        jLabel96.setText("-20 Altın");
        jPanel5.add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, 150, 30));

        jButton1.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(51, 204, 0));
        jButton1.setText("100 Asker Al");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, 40));

        jButton3.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(51, 204, 0));
        jButton3.setText("1k Asker Al");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 130, 40));

        jLabel97.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(204, 0, 51));
        jLabel97.setText("-200 Altın");
        jPanel5.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 150, 30));

        jButton4.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(204, 0, 51));
        jButton4.setText("1k Asker Terhis Et");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 190, 40));

        jButton2.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(204, 0, 51));
        jButton2.setText("100 Asker Terhis Et");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 190, 40));

        jTabbedPane2.addTab("tab6", jPanel5);

        getContentPane().add(jTabbedPane2);
        jTabbedPane2.setBounds(420, 240, 530, 240);

        Button_Halk.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        Button_Halk.setText("Halk");
        Button_Halk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_HalkActionPerformed(evt);
            }
        });
        getContentPane().add(Button_Halk);
        Button_Halk.setBounds(420, 60, 110, 40);

        Button_Ordu.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        Button_Ordu.setText("Ordu");
        Button_Ordu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_OrduActionPerformed(evt);
            }
        });
        getContentPane().add(Button_Ordu);
        Button_Ordu.setBounds(540, 60, 110, 40);

        jLabel4.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Vilayet Sayısı:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(180, 60, 150, 32);

        Label_Provinces.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        Label_Provinces.setForeground(new java.awt.Color(255, 255, 255));
        Label_Provinces.setText("5");
        getContentPane().add(Label_Provinces);
        Label_Provinces.setBounds(340, 60, 40, 30);

        Tur_Button.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        Tur_Button.setText("Tur Atla");
        Tur_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Tur_ButtonActionPerformed(evt);
            }
        });
        getContentPane().add(Tur_Button);
        Tur_Button.setBounds(10, 330, 130, 130);

        jLabel8.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Altın:");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(0, 100, 60, 32);

        Label_TurGeliri.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        Label_TurGeliri.setForeground(new java.awt.Color(255, 255, 255));
        Label_TurGeliri.setText("150");
        getContentPane().add(Label_TurGeliri);
        Label_TurGeliri.setBounds(230, 250, 100, 32);

        jLabel43.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Sonraki Turun Geliri:");
        getContentPane().add(jLabel43);
        jLabel43.setBounds(0, 250, 230, 32);

        jLabel44.setFont(new java.awt.Font("sansserif", 0, 20)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Tur Sayısı:");
        getContentPane().add(jLabel44);
        jLabel44.setBounds(140, 380, 100, 30);

        Label_TourCount.setFont(new java.awt.Font("sansserif", 0, 20)); // NOI18N
        Label_TourCount.setForeground(new java.awt.Color(255, 255, 255));
        Label_TourCount.setText("0");
        getContentPane().add(Label_TourCount);
        Label_TourCount.setBounds(240, 380, 50, 30);

        Savas_Button1.setIcon(new javax.swing.ImageIcon("C:\\Users\\ismai\\Downloads\\Proje\\1038032.png")); // NOI18N
        Savas_Button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Savas_Button1ActionPerformed(evt);
            }
        });
        getContentPane().add(Savas_Button1);
        Savas_Button1.setBounds(10, 550, 130, 130);

        jLabel7.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Asker Sayısı:");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(0, 180, 140, 32);

        jLabel13.setFont(new java.awt.Font("sansserif", 0, 36)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("İstatistikler");
        jLabel13.setToolTipText("");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(0, 0, 384, 47);

        jLabel17.setFont(new java.awt.Font("sansserif", 0, 36)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Kuzey Krallığı");
        jLabel17.setToolTipText("");
        getContentPane().add(jLabel17);
        jLabel17.setBounds(40, 490, 384, 47);

        KKProvinces_Label.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        KKProvinces_Label.setForeground(new java.awt.Color(255, 255, 255));
        KKProvinces_Label.setText("5");
        getContentPane().add(KKProvinces_Label);
        KKProvinces_Label.setBounds(350, 620, 50, 32);

        jLabel19.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Asker Sayısı:");
        getContentPane().add(jLabel19);
        jLabel19.setBounds(150, 570, 140, 32);

        KKArmySize_Label.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        KKArmySize_Label.setForeground(new java.awt.Color(255, 255, 255));
        KKArmySize_Label.setText("300");
        getContentPane().add(KKArmySize_Label);
        KKArmySize_Label.setBounds(300, 570, 80, 32);

        jLabel41.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 255, 255));
        jLabel41.setText("Kuzey Krallığı");
        getContentPane().add(jLabel41);
        jLabel41.setBounds(750, 540, 100, 21);

        jLabel56.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel56.setText("Kayıplar: ");
        getContentPane().add(jLabel56);
        jLabel56.setBounds(410, 600, 70, 19);

        jLabel57.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(0, 51, 255));
        jLabel57.setText("Siz");
        getContentPane().add(jLabel57);
        jLabel57.setBounds(430, 540, 40, 19);

        CasualitySize_Label.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        CasualitySize_Label.setText("-");
        getContentPane().add(CasualitySize_Label);
        CasualitySize_Label.setBounds(480, 600, 50, 19);

        jLabel80.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel80.setText("Kayıplar: ");
        getContentPane().add(jLabel80);
        jLabel80.setBounds(750, 600, 70, 21);

        KKActiveForces_Label.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        KKActiveForces_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        KKActiveForces_Label.setText("-");
        getContentPane().add(KKActiveForces_Label);
        KKActiveForces_Label.setBounds(640, 580, 70, 21);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(400, 480, 20, 350);
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(400, 480, 500, 10);

        jLabel99.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel99.setText("Asker Sayısı: ");
        getContentPane().add(jLabel99);
        jLabel99.setBounds(750, 570, 90, 21);

        jLabel18.setText("VS");
        getContentPane().add(jLabel18);
        jLabel18.setBounds(620, 580, 20, 16);

        jLabel100.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel100.setText("Muharebe Süreci");
        getContentPane().add(jLabel100);
        jLabel100.setBounds(560, 540, 150, 21);

        KKCasualities_Label.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        KKCasualities_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        KKCasualities_Label.setText("-");
        getContentPane().add(KKCasualities_Label);
        KKCasualities_Label.setBounds(640, 620, 70, 21);

        ActiveForces_Label.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        ActiveForces_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ActiveForces_Label.setText("-");
        getContentPane().add(ActiveForces_Label);
        ActiveForces_Label.setBounds(550, 580, 70, 21);

        Casualities_Label.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        Casualities_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Casualities_Label.setText("-");
        getContentPane().add(Casualities_Label);
        Casualities_Label.setBounds(550, 620, 70, 21);

        jButton5.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jButton5.setText("Devam");
        jButton5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5);
        jButton5.setBounds(550, 650, 170, 40);

        jLabel79.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel79.setText("Asker Sayısı: ");
        getContentPane().add(jLabel79);
        jLabel79.setBounds(410, 570, 90, 19);

        KKCasualitySize_Label.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        KKCasualitySize_Label.setText("-");
        getContentPane().add(KKCasualitySize_Label);
        KKCasualitySize_Label.setBounds(820, 600, 50, 19);

        BattleArmySize_Label.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        BattleArmySize_Label.setText("-");
        getContentPane().add(BattleArmySize_Label);
        BattleArmySize_Label.setBounds(500, 570, 50, 19);

        KKBattleArmySize_Label.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        KKBattleArmySize_Label.setText("-");
        getContentPane().add(KKBattleArmySize_Label);
        KKBattleArmySize_Label.setBounds(840, 570, 50, 19);

        setBounds(0, 0, 909, 736);
    }// </editor-fold>//GEN-END:initComponents

    private void Button_OrduActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_OrduActionPerformed
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_Button_OrduActionPerformed

    private void Button_SoylularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_SoylularActionPerformed
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_Button_SoylularActionPerformed

    private void Button_HalkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_HalkActionPerformed
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_Button_HalkActionPerformed

    private void Button_BurjuvaziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_BurjuvaziActionPerformed
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_Button_BurjuvaziActionPerformed

    private void Button_HalkVergilerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_HalkVergilerActionPerformed
        jTabbedPane2.setSelectedIndex(0);
    }//GEN-LAST:event_Button_HalkVergilerActionPerformed

    private void Button_AkreAlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_AkreAlActionPerformed
        if(gold>=300){
            gold-=300;
            acres+=1;
            GoldChange();
            AcreChange();
            FFPCalculator();
        }
        else{
            JOptionPane.showMessageDialog(null,"Yeterli paran yok!","Uyarı Mesajı",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_Button_AkreAlActionPerformed

    private void Button_AskerEqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_AskerEqActionPerformed
        jTabbedPane2.setSelectedIndex(1);
    }//GEN-LAST:event_Button_AskerEqActionPerformed

    private void Button_ConscriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_ConscriptionActionPerformed
        jTabbedPane2.setSelectedIndex(2);
    }//GEN-LAST:event_Button_ConscriptionActionPerformed

    private void Button_Soylular1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_Soylular1ActionPerformed
        jTabbedPane2.setSelectedIndex(3);
    }//GEN-LAST:event_Button_Soylular1ActionPerformed

    private void Button_Soylular2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_Soylular2ActionPerformed
        jTabbedPane2.setSelectedIndex(4);
    }//GEN-LAST:event_Button_Soylular2ActionPerformed

    private void Tur_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Tur_ButtonActionPerformed
        tourCount++;
        Label_TourCount.setText(Integer.toString(tourCount));
        
        //Bug Önlemi
        jToggleButton9.show();
        jToggleButton10.show();
        jToggleButton11.show();
        jToggleButton12.show();
        jToggleButton13.show();
        
        //money
        IncomeChange();
       
        gold+=income;
        GoldChange();
        
        //Pops
        popNum+=growth;
        popKNumber+=growth*1000;
        PopChange();
        
        FFPCalculator();
        ManpowerChange();
        SoldierChange();
        
        //KK
        KKarmyAdder();
        KKattackChanceEvent();
        
        tempTourCount--;
        if(tempTourCount<1){
            truce=false;
        }
        
        //WinLoseMechanism
        if(KKprovinces<=0){
            JOptionPane.showMessageDialog(null,"Tebrikler oyunu kazandınız!","Bilgi Mesajı",JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }
        else if(provinces<=0){
            JOptionPane.showMessageDialog(null,"Malesef oyunu kaybettiniz!","Bilgi Mesajı",JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }
    }//GEN-LAST:event_Tur_ButtonActionPerformed

    private void Button_Conscription1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_Conscription1ActionPerformed
        jTabbedPane2.setSelectedIndex(5);
    }//GEN-LAST:event_Button_Conscription1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(armySize>=100){
            armySize-=100;
            SoldierChange();
            IncomeChange();
        }
        else{
            JOptionPane.showMessageDialog(null,"Terhis etmeye yeterli askerin yok!","Uyarı Mesajı",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if(armySize>=1000){
            armySize-=1000;
            SoldierChange();
            IncomeChange();
        }
        else{
            JOptionPane.showMessageDialog(null,"Terhis etmeye yeterli askerin yok!","Uyarı Mesajı",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(gold>=200 && manpower>=armySize+1000){
            gold-=200;
            armySize+=1000;
            GoldChange();
            SoldierChange();
            IncomeChange();
        }
        else{
            JOptionPane.showMessageDialog(null,"Yeterli paran yok veya azami askeri personeli sayısına ulaştın!","Uyarı Mesajı",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(gold>=20 && manpower>=armySize+100){
            gold-=20;
            armySize+=100;
            GoldChange();
            SoldierChange();
            IncomeChange();
        }
        else{
            JOptionPane.showMessageDialog(null,"Yeterli paran yok veya azami askeri personeli sayısına ulaştın!","Uyarı Mesajı",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jToggleButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton33ActionPerformed
        bourgeoisieTaxRate=30;
        bourgeoisieGrowthContribution=-3;
        IncomeChange();
        GrowthChange();
    }//GEN-LAST:event_jToggleButton33ActionPerformed

    private void jToggleButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton32ActionPerformed
        bourgeoisieTaxRate=-30;
        bourgeoisieGrowthContribution=3;
        IncomeChange();
        GrowthChange();
    }//GEN-LAST:event_jToggleButton32ActionPerformed

    private void jToggleButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton28ActionPerformed
        bourgeoisieTaxRate=-10;
        bourgeoisieGrowthContribution=1;
        IncomeChange();
        GrowthChange();
    }//GEN-LAST:event_jToggleButton28ActionPerformed

    private void jToggleButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton31ActionPerformed
        bourgeoisieTaxRate=10;
        bourgeoisieGrowthContribution=-1;
        IncomeChange();
        GrowthChange();
    }//GEN-LAST:event_jToggleButton31ActionPerformed

    private void jToggleButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton26ActionPerformed
        bourgeoisieTaxRate=0;
        bourgeoisieGrowthContribution=0;
        IncomeChange();
        GrowthChange();
    }//GEN-LAST:event_jToggleButton26ActionPerformed

    private void jToggleButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton29ActionPerformed
        bourgeoisieTaxRate=-20;
        bourgeoisieGrowthContribution=2;
        IncomeChange();
        GrowthChange();
    }//GEN-LAST:event_jToggleButton29ActionPerformed

    private void jToggleButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton27ActionPerformed
        bourgeoisieTaxRate=20;
        bourgeoisieGrowthContribution=-2;
        IncomeChange();
        GrowthChange();
    }//GEN-LAST:event_jToggleButton27ActionPerformed

    private void jToggleButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton23ActionPerformed
        nobleSoldierRate=8;
        nobleTaxRate=-10;
        IncomeChange();
        SoldierChange();
    }//GEN-LAST:event_jToggleButton23ActionPerformed

    private void jToggleButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton20ActionPerformed
        nobleSoldierRate=2;
        nobleTaxRate=20;
        IncomeChange();
        SoldierChange();
    }//GEN-LAST:event_jToggleButton20ActionPerformed

    private void jToggleButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton21ActionPerformed
        nobleSoldierRate=4;
        nobleTaxRate=10;
        IncomeChange();
        SoldierChange();
    }//GEN-LAST:event_jToggleButton21ActionPerformed

    private void jToggleButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton24ActionPerformed
        nobleSoldierRate=10;
        nobleTaxRate=-20;
        IncomeChange();
        SoldierChange();
    }//GEN-LAST:event_jToggleButton24ActionPerformed

    private void jToggleButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton22ActionPerformed
        nobleSoldierRate=6;
        nobleTaxRate=0;
        IncomeChange();
        SoldierChange();
    }//GEN-LAST:event_jToggleButton22ActionPerformed

    private void jToggleButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton15ActionPerformed
        conscriptionRate=20;
        conscriptTax=-20;
        ManpowerChange();
        ManpowerChangeChecker();
        IncomeChange();
    }//GEN-LAST:event_jToggleButton15ActionPerformed

    private void jToggleButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton16ActionPerformed
        conscriptionRate=15;
        conscriptTax=-10;
        ManpowerChange();
        ManpowerChangeChecker();
        IncomeChange();
    }//GEN-LAST:event_jToggleButton16ActionPerformed

    private void jToggleButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton17ActionPerformed
        conscriptionRate=10;
        conscriptTax=0;
        ManpowerChange();
        ManpowerChangeChecker();
        IncomeChange();
    }//GEN-LAST:event_jToggleButton17ActionPerformed

    private void jToggleButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton18ActionPerformed
        conscriptionRate=5;
        conscriptTax=10;
        ManpowerChange();
        ManpowerChangeChecker();
        IncomeChange();
    }//GEN-LAST:event_jToggleButton18ActionPerformed

    private void jToggleButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton19ActionPerformed
        conscriptionRate=3;
        conscriptTax=20;
        ManpowerChange();
        ManpowerChangeChecker();
        IncomeChange();
    }//GEN-LAST:event_jToggleButton19ActionPerformed

    private void jToggleButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton13ActionPerformed
        armyQuality=10;
        armyPayments=50;
        ArmyQualityChane();
        IncomeChange();
        jToggleButton9.hide();
        jToggleButton10.hide();
        jToggleButton11.hide();
        jToggleButton12.hide();
        jToggleButton13.hide();
    }//GEN-LAST:event_jToggleButton13ActionPerformed

    private void jToggleButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton12ActionPerformed
        armyQuality=8;
        armyPayments=40;
        ArmyQualityChane();
        IncomeChange();
        jToggleButton9.hide();
        jToggleButton10.hide();
        jToggleButton11.hide();
        jToggleButton12.hide();
        jToggleButton13.hide();
    }//GEN-LAST:event_jToggleButton12ActionPerformed

    private void jToggleButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton11ActionPerformed
        armyQuality=6;
        armyPayments=30;
        ArmyQualityChane();
        IncomeChange();
        jToggleButton9.hide();
        jToggleButton10.hide();
        jToggleButton11.hide();
        jToggleButton12.hide();
        jToggleButton13.hide();
    }//GEN-LAST:event_jToggleButton11ActionPerformed

    private void jToggleButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton10ActionPerformed
        armyQuality=4;
        armyPayments=20;
        ArmyQualityChane();
        IncomeChange();
        jToggleButton9.hide();
        jToggleButton10.hide();
        jToggleButton11.hide();
        jToggleButton12.hide();
        jToggleButton13.hide();
    }//GEN-LAST:event_jToggleButton10ActionPerformed

    private void jToggleButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton9ActionPerformed
        armyQuality=2;
        armyPayments=10;
        ArmyQualityChane();
        IncomeChange();
        jToggleButton9.hide();
        jToggleButton10.hide();
        jToggleButton11.hide();
        jToggleButton12.hide();
        jToggleButton13.hide();
    }//GEN-LAST:event_jToggleButton9ActionPerformed

    private void jToggleButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton7ActionPerformed
        popTaxRate=50;
        volunteerConscription=-10;
        ManpowerChange();
        ManpowerChangeChecker();
        IncomeChange();
    }//GEN-LAST:event_jToggleButton7ActionPerformed

    private void jToggleButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton6ActionPerformed
        popTaxRate=25;
        volunteerConscription=-6;
        ManpowerChange();
        ManpowerChangeChecker();
        IncomeChange();
    }//GEN-LAST:event_jToggleButton6ActionPerformed

    private void jToggleButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton5ActionPerformed
        popTaxRate=10;
        volunteerConscription=-3;
        ManpowerChange();
        ManpowerChangeChecker();
        IncomeChange();
    }//GEN-LAST:event_jToggleButton5ActionPerformed

    private void jToggleButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton4ActionPerformed
        popTaxRate=0;
        volunteerConscription=0;
        ManpowerChange();
        ManpowerChangeChecker();
        IncomeChange();
    }//GEN-LAST:event_jToggleButton4ActionPerformed

    private void jToggleButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton3ActionPerformed
        popTaxRate=-10;
        volunteerConscription=3;
        ManpowerChange();
        ManpowerChangeChecker();
        IncomeChange();
    }//GEN-LAST:event_jToggleButton3ActionPerformed

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        popTaxRate=-25;
        volunteerConscription=6;
        ManpowerChange();
        ManpowerChangeChecker();
        IncomeChange();
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        popTaxRate=-50;
        volunteerConscription=10;
        ManpowerChange();
        ManpowerChangeChecker();
        IncomeChange();
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void Savas_Button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Savas_Button1ActionPerformed
        war=true;
        if(truce==false){
            WarEvent();
        }
        else{
        JOptionPane.showMessageDialog(null, "Ateşkes sırasında muharebeye girişemezsiniz!","Uyarı Mesajı",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_Savas_Button1ActionPerformed
    int totalCasulties=0,KKtotalCasulties=0;
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Random battleDice=new Random();
        ActiveForces_Label.setText(Integer.toString(totalArmySize/5));
        KKActiveForces_Label.setText(Integer.toString(KKarmySize/5));
        totalPower=((totalArmySize/5)*armyQuality*(2*(1+battleDice.nextInt(5)))/100);
        KKtotalPower=((KKarmySize/5)*KKarmyQuality*(2*(1+battleDice.nextInt(5)))/100);
        totalArmySize-=KKtotalPower;
        KKarmySize-=totalPower;
        totalCasulties+=KKtotalPower;
        KKtotalCasulties+=totalPower;
        CasualitySize_Label.setText("-"+Integer.toString(totalCasulties));
        KKCasualitySize_Label.setText("-"+Integer.toString(KKtotalCasulties));
        Casualities_Label.setText("-"+Integer.toString(KKtotalPower));
        KKCasualities_Label.setText("-"+Integer.toString(totalPower));
        
        if(totalArmySize<=0){
            armySize=0;
            victory=false;
            war=false;
            WarEvent();
        }
        else if(KKarmySize<=0){
            KKarmySize=0;
            victory=true;
            war=false;
            WarEvent();
        }
    }//GEN-LAST:event_jButton5ActionPerformed
        
    
    
    
    public static void main(String args[]) {
        
        
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ActiveForces_Label;
    private javax.swing.JLabel BattleArmySize_Label;
    private javax.swing.JButton Button_AkreAl;
    private javax.swing.JButton Button_AskerEq;
    private javax.swing.JButton Button_Burjuvazi;
    private javax.swing.JButton Button_Conscription;
    private javax.swing.JButton Button_Conscription1;
    private javax.swing.JButton Button_Halk;
    private javax.swing.JButton Button_HalkVergiler;
    private javax.swing.JButton Button_Ordu;
    private javax.swing.JButton Button_Soylular;
    private javax.swing.JButton Button_Soylular1;
    private javax.swing.JButton Button_Soylular2;
    private javax.swing.JLabel Casualities_Label;
    private javax.swing.JLabel CasualitySize_Label;
    private javax.swing.JLabel KKActiveForces_Label;
    private javax.swing.JLabel KKArmySize_Label;
    private javax.swing.JLabel KKBattleArmySize_Label;
    private javax.swing.JLabel KKCasualities_Label;
    private javax.swing.JLabel KKCasualitySize_Label;
    private javax.swing.JLabel KKProvinces_Label;
    private javax.swing.JLabel Label_Acre;
    private javax.swing.JLabel Label_ArmyQuality;
    private javax.swing.JLabel Label_Content;
    private javax.swing.JLabel Label_FPP;
    private javax.swing.JLabel Label_Gold;
    private javax.swing.JLabel Label_Growth;
    private javax.swing.JLabel Label_Manpower;
    private javax.swing.JLabel Label_PopNum;
    private javax.swing.JLabel Label_Provinces;
    private javax.swing.JLabel Label_Soldiers;
    private javax.swing.JLabel Label_TourCount;
    private javax.swing.JLabel Label_TurGeliri;
    private javax.swing.JButton Savas_Button1;
    private javax.swing.JButton Tur_Button;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton10;
    private javax.swing.JToggleButton jToggleButton11;
    private javax.swing.JToggleButton jToggleButton12;
    private javax.swing.JToggleButton jToggleButton13;
    private javax.swing.JToggleButton jToggleButton15;
    private javax.swing.JToggleButton jToggleButton16;
    private javax.swing.JToggleButton jToggleButton17;
    private javax.swing.JToggleButton jToggleButton18;
    private javax.swing.JToggleButton jToggleButton19;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton20;
    private javax.swing.JToggleButton jToggleButton21;
    private javax.swing.JToggleButton jToggleButton22;
    private javax.swing.JToggleButton jToggleButton23;
    private javax.swing.JToggleButton jToggleButton24;
    private javax.swing.JToggleButton jToggleButton26;
    private javax.swing.JToggleButton jToggleButton27;
    private javax.swing.JToggleButton jToggleButton28;
    private javax.swing.JToggleButton jToggleButton29;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton jToggleButton31;
    private javax.swing.JToggleButton jToggleButton32;
    private javax.swing.JToggleButton jToggleButton33;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JToggleButton jToggleButton5;
    private javax.swing.JToggleButton jToggleButton6;
    private javax.swing.JToggleButton jToggleButton7;
    private javax.swing.JToggleButton jToggleButton9;
    // End of variables declaration//GEN-END:variables
}
