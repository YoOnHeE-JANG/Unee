package Control;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.text.TabSet;

import com.sun.javafx.image.impl.ByteIndexed.Getter;

import Model.CoronationVO;
import Model.RentalVO;
import Model.SuperlativesVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RootController implements Initializable {

	@FXML
	private TableView<SuperlativesVO> tableView = new TableView<>(); // ����� ��� ���̺�
	@FXML
	private TableView<CoronationVO> CoTableView = new TableView<>(); // ��� ���ε�� ���̺�
	@FXML
	private TableView<RentalVO> RentalView = new TableView<>(); // ������� ���뿩���̺�
	@FXML
	private TextField txtP_Name; // ����ڸ�
	@FXML
	private Button btnSearch; // �����Ȳ���� ����� �˻� ��ư
	@FXML
	private Button btnEdit; // �����Ȳ���� �������� ��ư
	@FXML
	private Button btnTotalList; // ��������ư
	@FXML
	private Button btnDelete; // �����Ȳ���� ������� ��� �����ϴ� ��ư
	@FXML
	private Button btnRental; // ���뿩->�뿩â�� ����ִ� ��ư
	@FXML
	private Button btnMonthCost; // ����������� �׷��� ��ư
	@FXML
	private Button btnC_Pay; // ��������Ϸ� ��ư
	@FXML
	private Button btnR_Pay; // �뿩�����Ϸ� ��ư
	@FXML
	private Button btnD_Pay; // �ļհ����Ϸ� ��ư
	@FXML
	private TextField txtGroupName; // ��ü��
	@FXML
	private TextField txtTitle; // ������
	@FXML
	private ComboBox<String> cbContact; // ����ó �޺�
	@FXML
	private TextField txtContactAll; // ����ó ��ü
	@FXML
	private TextField txtContact1; // ����ó ���ڸ�
	@FXML
	private TextField txtContact2; // ����ó ���ڸ�
	@FXML
	private TextField txtPrize; // ����ڵ�Ϲ�ȣ
	@FXML
	private TextField txtAddress;// �ּ�

	@FXML
	private TextField txtC_Search; // �����Ȳ���� �˻��� - ����ڸ�˻�
	@FXML
	private Button btnC_Search; // �����Ȳ���� �˻� - �˻� ��ư
	@FXML
	private Button btnC_Totallist; // �����Ȳ��ü���� ��ư
	@FXML
	private Button btnC_Delete; // �����Ȳ ���� ���� ���� ��ư
	@FXML
	private ToggleGroup hallGroup; // ����׷�
	@FXML
	private RadioButton rbMae; // �ޱ���
	@FXML
	private RadioButton rbSo; // �ұ���
	@FXML
	private RadioButton rbDe; // �����
	@FXML
	private TextField txtHallName; // ������
	@FXML
	private Button btnOk; // �����Ϲ�ư
	@FXML
	private Button btnInit; // ����ʱ�ȭ��ư
	@FXML
	private DatePicker dpStart; // ���������
	@FXML
	private DatePicker dpEnd; // ���������
	@FXML
	private CheckBox chMorning1; // �غ�ð� -����
	@FXML
	private CheckBox chAfternoon1; // �غ�ð� -����
	@FXML
	private CheckBox chNight1; // �غ�ð� -ö��
	@FXML
	private TextField txtMorning1; // �غ� -����-����
	@FXML
	private TextField txtAfternoon1; // �غ�-����-����
	@FXML
	private TextField txtNight1; // �غ�-ö��-����
	@FXML
	private CheckBox chMorning2; // ö�Žð� - ����
	@FXML
	private CheckBox chAfternoon2; // ö�Žð� -����
	@FXML
	private CheckBox chNight2; // ö�Žð� -ö��
	@FXML
	private TextField txtMorning2; // ö�Žð� -����-����
	@FXML
	private TextField txtAfternoon2; // ö�Žð�-����-����
	@FXML
	private TextField txtNight2; // ö�Žð�-ö��-����
	@FXML
	private TextField txtD_day; // ������ ����
	@FXML
	private Button btnC_Total; // ����Ѿ����� ��ư
	@FXML
	private TextField txtC_Total; // ����Ѿ�����
	@FXML
	private Button btnR_Search; // �뿩��Ȳ ����ڸ� �˻���ư
	@FXML
	private Button btnR_Edit; // ���뿩��� ��ư

	@FXML
	private Button btnR_Delete; // ������������� ��ư
	@FXML
	private Button btnR_Return; // �ݳ��� �뿩��->�ݳ����� ���� ��ư
	@FXML
	private Button btnR_Damage; // �ļ�û�� ��ư
	@FXML
	private DatePicker dpR_Start; // ��ǰ�뿩��
	@FXML
	private DatePicker dpR_End; // ��ǰ��ǰ��
	@FXML
	private Button btnR_CostInfo; // �뿩�������
	@FXML
	private CheckBox chFloor; // ������üũ
	@FXML
	private CheckBox chFog; // ���׸ӽ� üũ
	@FXML
	private CheckBox chLodder; // ��ٸ� üũ
	@FXML
	private CheckBox chCanterbury; // ����� üũ
	@FXML
	private CheckBox chCloth; // �渷õ üũ
	@FXML
	private CheckBox chCool; // �ù� üũ
	@FXML
	private CheckBox chHeat; // ���� üũ
	@FXML
	private CheckBox chLConsol; // ����� �ܼ� üũ
	@FXML
	private CheckBox chMoving; // ������ üũ
	@FXML
	private CheckBox chPar_64; // ���� üũ
	@FXML
	private CheckBox chPar_32; // ���� üũ
	@FXML
	private CheckBox chElip; // �������̴� üũ
	@FXML
	private CheckBox chLaser; // ������ üũ
	@FXML
	private CheckBox chMConsol; // ����� �ܼ� üũ
	@FXML
	private CheckBox chWoofer; // ���� üũ
	@FXML
	private CheckBox chCmic; // ��������ũ üũ
	@FXML
	private CheckBox chWmic; // ��������ũüũ
	@FXML
	private CheckBox chSpeaker; // ����Ŀ üũ
	@FXML
	private CheckBox chPwAmp; // �Ŀ����� üũ
	@FXML
	private TextField txtFloor_P; // ������ �뿩 ����
	@FXML
	private TextField txtFloor_N; // ������ ���� ����
	@FXML
	private TextField txtLodder_P; // ��ٸ� �뿩 ����
	@FXML
	private TextField txtLodder_N; // ��ٸ� ���� ����
	@FXML
	private TextField txtCloth_P; // �渷õ �뿩 ����
	@FXML
	private TextField txtCloth_N; // �渷õ ���� ����
	@FXML
	private TextField txtFog_P; // ����� �뿩 ����
	@FXML
	private TextField txtFog_N; // ����� ���� ����
	@FXML
	private TextField txtCanterbury_P; // ����� �뿩 ����
	@FXML
	private TextField txtCanterbury_N; // ����� ���� ����
	@FXML
	private TextField txtLConsol_P; // ���� �ܼ� �뿩 ����
	@FXML
	private TextField txtLConsol_N; // ���� �ܼ� ���� ����
	@FXML
	private TextField txtMoving_P; // ���� �뿩 ����
	@FXML
	private TextField txtMoving_N; // ���� ���� ����
	@FXML
	private TextField txtPar_64_P; // ���� �뿩 ����
	@FXML
	private TextField txtPar_64_N; // ���� ���� ����
	@FXML
	private TextField txtPar_32_P; // ���� �뿩 ����
	@FXML
	private TextField txtPar_32_N; // ���� ���� ����
	@FXML
	private TextField txtElip_P; // ���� �뿩 ����
	@FXML
	private TextField txtElip_N; // ���� ���� ����
	@FXML
	private TextField txtLaser_P; // ������ �뿩 ����
	@FXML
	private TextField txtLaser_N; // ������ ���� ����
	@FXML
	private TextField txtMConsol_P; // ����� �ܼ� �뿩 ����
	@FXML
	private TextField txtMConsol_N; // ����� �ܼ� ���� ����
	@FXML
	private TextField txtWoofer_P; // ���� �뿩 ����
	@FXML
	private TextField txtWoofer_N; // ���� ���� ����
	@FXML
	private TextField txtCmic_P; // ��������ũ �뿩 ����
	@FXML
	private TextField txtCmic_N; // ��������ũ ���� ����
	@FXML
	private TextField txtWmic_P; // ��������ũ �뿩 ����
	@FXML
	private TextField txtWmic_N; // ��������ũ ���� ����
	@FXML
	private TextField txtSpeaker_P; // ����Ŀ �뿩����
	@FXML
	private TextField txtSpeaker_N; // ����Ŀ �������
	@FXML
	private TextField txtPwAmp_P; // �Ŀ� ���� �뿩 ����
	@FXML
	private TextField txtPwAmp_N; // �Ŀ� ���� ���� ����
	@FXML
	private Button btnR_Total; // �뿩 �Ѿ� ���� ��ư
	@FXML
	private TextField txtR_Total; // �뿩 �Ѿ� ���
	@FXML
	private Button btnR_Ok; // �뿩 ��� ��ư
	@FXML
	private Button btnR_Init; // �뿩 �ʱ�ȭ
	@FXML
	private Button btnR_Exit; // â�ݱ� ��ư
	@FXML
	private TextField txtSearch;// �˻��ʵ�
	@FXML
	private TextField txtR_Search; // �뿩�˻��ʵ�
	@FXML
	private Button btnR_TotalList; // �뿩��ü���� ��ư
	@FXML
	private Button btnPOk; // ����� ��� ��ư
	@FXML
	private Button btnC_Info; // ��������Է� ��ư
	@FXML
	private TextField txtPayMent; // ����� ����
	@FXML
	private TextField txtR_Name; // �뿩���� ����ڸ�
	@FXML
	private Tab RentalTab; // �뿩��
	@FXML
	private TabPane tabpane; // ����

	SuperlativesVO sVo = new SuperlativesVO();
	CoronationVO cVo = new CoronationVO();
	ObservableList<SuperlativesVO> data = FXCollections.observableArrayList();
	ObservableList<CoronationVO> CoData = FXCollections.observableArrayList();
	ObservableList<RentalVO> ReData = FXCollections.observableArrayList();
	ObservableList<SuperlativesVO> selectSuperlativesVO = null; // ���̺��� ������ ���� ����
	ObservableList<CoronationVO> selectCoronationVO = null;
	ObservableList<RentalVO> selectRentalVO = null;
	boolean editDelete = false; // ������ �� Ȯ�� ��ư ���� ����
	int selectedIndex; // ���̺��� ������ ����� ���� �ε��� ����
	int no; // ������ ���̺��� ������ �����ȣ ����
	String name; // ������ ���̺��� ������ �̸� ����

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// ��ư �� �ؽ�Ʈ �ʵ��ʱ���� ����
		rbMae.setDisable(true);
		rbSo.setDisable(true);
		rbDe.setDisable(true);
		dpStart.setDisable(true);
		dpEnd.setDisable(true);
		chMorning1.setDisable(true);
		chAfternoon1.setDisable(true);
		chNight1.setDisable(true);
		chMorning2.setDisable(true);
		chAfternoon2.setDisable(true);
		chNight2.setDisable(true);
		txtMorning1.setEditable(false);
		txtAfternoon1.setEditable(false);
		txtNight1.setEditable(false);
		txtMorning2.setEditable(false);
		txtAfternoon2.setEditable(false);
		txtNight2.setEditable(false);
		txtD_day.setEditable(false);
		btnC_Total.setDisable(true);
		txtC_Total.setEditable(false);
		btnOk.setDisable(true);
		btnEdit.setDisable(true);

		// ����ڵ�� ���̺�信 �� �÷� �̸�����
		TableColumn colP_Name = new TableColumn("����ڸ�");
		colP_Name.setMinWidth(80);
		colP_Name.setStyle("-fx-alignment:CENTER");
		colP_Name.setCellValueFactory(new PropertyValueFactory<>("p_Name"));
		TableColumn colGroupName = new TableColumn("��ü��");
		colGroupName.setMinWidth(120);
		colGroupName.setStyle("-fx-alignment:CENTER");
		colGroupName.setCellValueFactory(new PropertyValueFactory<>("groupName"));
		TableColumn colTitle = new TableColumn("������");
		colTitle.setMinWidth(150);
		colTitle.setStyle("-fx-alignment:CENTER");
		colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
		TableColumn colAddress = new TableColumn("�ּ�");
		colAddress.setMinWidth(275);
		colAddress.setStyle("-fx-alignment:CENTER");
		colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
		TableColumn colContactAll = new TableColumn("����ó");
		colContactAll.setMinWidth(115);
		colContactAll.setStyle("-fx-alignment:CENTER");
		colContactAll.setCellValueFactory(new PropertyValueFactory<>("contactAll"));
		TableColumn colPrize = new TableColumn("�����Ϲ�ȣ");
		colPrize.setMinWidth(100);
		colPrize.setStyle("-fx-alignment:CENTER");

		colPrize.setCellValueFactory(new PropertyValueFactory<>("prize"));

		tableView.getColumns().addAll(colP_Name, colGroupName, colTitle, colAddress, colContactAll, colPrize);

		// ����� ��ü ����
		totalList();
		tableView.setItems(data);

		// ------------------------------------------------------------------------------------------------------

		// ���������� ���̺�信 �� Į�� �̸�����

		TableColumn colC_No = new TableColumn("�����ȣ");
		colC_No.setMinWidth(40);
		colC_No.setStyle("-fx-alignment:CENTER");
		colC_No.setCellValueFactory(new PropertyValueFactory<>("c_No"));
		TableColumn colP_Name1 = new TableColumn("����ڸ�");
		colP_Name1.setMinWidth(80);
		colP_Name1.setStyle("-fx-alignment:CENTER");
		colP_Name1.setCellValueFactory(new PropertyValueFactory<>("p_Name"));
		TableColumn colHall = new TableColumn("������");
		colHall.setMinWidth(40);
		colHall.setStyle("-fx-alignment:CENTER");
		colHall.setCellValueFactory(new PropertyValueFactory<>("hall"));
		TableColumn colStart_Date = new TableColumn("���������");
		colStart_Date.setMinWidth(90);
		colStart_Date.setStyle("-fx-alignment:CENTER");
		colStart_Date.setCellValueFactory(new PropertyValueFactory<>("start_Date"));
		TableColumn colEnd_Date = new TableColumn("���������");
		colEnd_Date.setMinWidth(90);
		colEnd_Date.setStyle("-fx-alignment:CENTER");
		colEnd_Date.setCellValueFactory(new PropertyValueFactory<>("end_Date"));
		TableColumn colC_Total = new TableColumn("�����");
		colC_Total.setMinWidth(120);
		colC_Total.setStyle("-fx-alignment:CENTER");
		colC_Total.setCellValueFactory(new PropertyValueFactory<>("c_Total"));
		TableColumn colC_PayMent = new TableColumn("����� ����");
		colC_PayMent.setMinWidth(80);
		colC_PayMent.setStyle("-fx-alignment:CENTER");
		colC_PayMent.setCellValueFactory(new PropertyValueFactory<>("c_PayMent"));

		CoTableView.getColumns().addAll(colC_No, colP_Name1, colHall, colStart_Date, colEnd_Date, colC_Total,
				colC_PayMent);

		// ����� ��ü ����
		totalList1();
		CoTableView.setItems(CoData);

		// ----------------------------------------------------------------------------------------------------

		TableColumn colR_No = new TableColumn("�뿩��ȣ");
		colR_No.setPrefWidth(60);
		colR_No.setStyle("-fx-alignment:center");
		colR_No.setCellValueFactory(new PropertyValueFactory<>("r_No"));
		TableColumn colC_No1 = new TableColumn("�����ȣ");
		colC_No1.setPrefWidth(60);
		colC_No1.setStyle("-fx-alignment:center");
		colC_No1.setCellValueFactory(new PropertyValueFactory<>("c_No1"));
		TableColumn colP_Name2 = new TableColumn("����ڸ�");
		colP_Name2.setMinWidth(70);
		colP_Name2.setStyle("-fx-alignment:center");
		colP_Name2.setCellValueFactory(new PropertyValueFactory<>("p_Name"));
		TableColumn colRental_StartDate = new TableColumn("�뿩��");
		colRental_StartDate.setMinWidth(80);
		colRental_StartDate.setStyle("-fx-alignment:center");
		colRental_StartDate.setCellValueFactory(new PropertyValueFactory<>("rental_StartDate"));
		TableColumn colRental_EndDate = new TableColumn("�ݳ���");
		colRental_EndDate.setMinWidth(80);
		colRental_EndDate.setStyle("-fx-alignment:center");
		colRental_EndDate.setCellValueFactory(new PropertyValueFactory<>("rental_EndDate"));
		TableColumn colDevice_P = new TableColumn("��ġ����");
		colDevice_P.setPrefWidth(60);
		colDevice_P.setStyle("-fx-alignment:center");
		colDevice_P.setCellValueFactory(new PropertyValueFactory<>("device_P"));
		TableColumn colLight_P = new TableColumn("����� ��");
		colLight_P.setPrefWidth(60);
		colLight_P.setStyle("-fx-alignment:center");
		colLight_P.setCellValueFactory(new PropertyValueFactory<>("light_P"));
		TableColumn colSound_P = new TableColumn("����� ��");
		colSound_P.setPrefWidth(60);
		colSound_P.setStyle("-fx-alignment:center");
		colSound_P.setCellValueFactory(new PropertyValueFactory<>("sound_P"));
		TableColumn colRental_Total = new TableColumn("�� ����");
		colRental_Total.setPrefWidth(60);
		colRental_Total.setStyle("-fx-alignment:center");
		colRental_Total.setCellValueFactory(new PropertyValueFactory<>("rental_Total"));
		TableColumn colRental_Pay = new TableColumn("�� �뿩��");
		colRental_Pay.setMinWidth(80);
		colRental_Pay.setStyle("-fx-alignment:center");
		colRental_Pay.setCellValueFactory(new PropertyValueFactory<>("rental_Pay"));
		TableColumn colRental_PayMent = new TableColumn("�뿩����");
		colRental_PayMent.setMinWidth(50);
		colRental_PayMent.setStyle("-fx-alignment:center");
		colRental_PayMent.setCellValueFactory(new PropertyValueFactory<>("rental_PayMent"));
		TableColumn colRental_Ok = new TableColumn("�뿩����");
		colRental_Ok.setMinWidth(50);
		colRental_Ok.setStyle("-fx-alignment:center");
		colRental_Ok.setCellValueFactory(new PropertyValueFactory<>("rental_Ok"));

		RentalView.getColumns().addAll(colR_No, colC_No1, colP_Name2, colRental_StartDate, colRental_EndDate,
				colDevice_P, colLight_P, colSound_P, colRental_Total, colRental_Pay, colRental_PayMent, colRental_Ok);

		totalList2();
		RentalView.setItems(ReData);

		// -----------------------------------------------------------------------------------------------------

		System.out.println("------1------");
		// ����ó �޺��ڽ��� �� ��
		cbContact.setItems(
				FXCollections.observableArrayList("010", "011", "016", "017", "018", "019", "02", "031", "032", "033",
						"041", "042", "043", "044", "051", "052", "053", "054", "055", "061", "062", "063", "064"));
		System.out.println("------2------");
		// �ؽ�Ʈ�ʵ� ���ڸ� �Է��ϵ��� ����------------------------------------
		DecimalFormat format = new DecimalFormat("##########");
		// ������� ����ó, ����ڵ�Ϲ�ȣ �ʵ忡 ���ڸ� �Է�

		txtPrize.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;

			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(), parsePosition);

			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 10) {
				return null;
			} else {
				return event;
			}
		}));
		// ����ð��� �����ʵ忡 ���ڸ� �Է�
		txtMorning1.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;

			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(), parsePosition);

			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 4) {
				return null;
			} else {
				return event;
			}
		}));
		txtAfternoon1.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;

			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(), parsePosition);

			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 4) {
				return null;
			} else {
				return event;
			}
		}));
		txtNight1.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;

			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(), parsePosition);

			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 4) {
				return null;
			} else {
				return event;
			}
		}));
		txtMorning2.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;

			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(), parsePosition);

			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 4) {
				return null;
			} else {
				return event;
			}
		}));
		txtAfternoon2.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;

			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(), parsePosition);

			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 4) {
				return null;
			} else {
				return event;
			}
		}));
		txtNight2.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;

			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(), parsePosition);

			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 4) {
				return null;
			} else {
				return event;
			}
		}));
		txtD_day.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;

			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(), parsePosition);

			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 4) {
				return null;
			} else {
				return event;
			}
		}));

		// ��ü���� �̱�
		btnTotalList.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					data.removeAll(data);
					// ����� ��ü ����
					totalList();
				} catch (Exception e) {

				}
			}
		});

		// ����� ���� ���
		btnPOk.setOnAction(event -> {
			try {
				data.removeAll(data);
				SuperlativesVO sVo = new SuperlativesVO();
				SuperlativesDAO sDao = new SuperlativesDAO();

				// ����� ���� ����
				if (event.getSource().equals(btnPOk)) {
					sVo = new SuperlativesVO(txtP_Name.getText(), txtGroupName.getText(), txtTitle.getText(),
							txtAddress.getText(),
							((cbContact.getValue() + "-" + txtContact1.getText() + "-" + txtContact2.getText())),
							txtPrize.getText());
					sDao = new SuperlativesDAO();
					sDao.getSuperlativesInfo(sVo);
					txtP_Name.clear();
					txtGroupName.clear();
					txtTitle.clear();
					txtAddress.clear();
					cbContact.getSelectionModel().clearSelection();
					txtContact1.clear();
					txtContact2.clear();
					txtPrize.clear();

					if (sDao != null) {
						totalList();
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("����� ���� �Է�");
						alert.setHeaderText(txtP_Name.getText() + "���� ������ ���������� �߰��Ǿ����ϴ�.");

						alert.showAndWait();

					}

				}

			} catch (Exception ie) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("����� ���� �Է�");
				alert.setHeaderText("����� ������ ��Ȯ�� �Է��Ͻÿ�.");
				alert.showAndWait();
			}
		});

		// �����Ȳ ��ü ����Ʈ����
		btnC_Totallist.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					CoData.removeAll(CoData);

					// �����Ȳ ��ü ����
					totalList1();
				} catch (Exception e) {

				}

			}

		});
		// -------------------------------------------------------------------------------------------------------------
		// ��� �Ѿ� ���ϱ�
		btnC_Total.setOnAction(event -> handlerBtnC_TotalAction(event));
		// ������̸����� ��ϵ� ��������� �˻�
		btnSearch.setOnAction(event -> handlerBtnSearchAction(event));
		// ����� ���̺� �̺�Ʈ ó��
		tableView.setOnMousePressed(event -> handlertableViewAction(event));
		// ������ ��ư
		btnEdit.setOnAction(event -> handlerBtnEditAction(event));
		// ������������Է¹�ư������ ���������Է°����ϰ� �ʵ�� ����
		btnC_Info.setOnAction(event -> handlerBtnC_InfoAction(event));
		// ����� ���� ���� ��ư
		btnDelete.setOnMouseClicked(event -> handlerBtnDeleteAction(event));
		// ������� ��������� ��� ��ư
		btnOk.setOnAction(event -> handlerBtnOkAction(event));
		// �����Ȳ���� ����� �˻� ��ư
		btnC_Search.setOnAction(event -> handlerBtnC_SearchAction(event));
		// ��������Է� �ؽ�Ʈ �ʵ� �ʱ�ȭ ��ư
		btnInit.setOnAction(event -> handlerBtnInitAction(event));
		// <������� ������� �̳� -> �ϳ� > �����ư
		btnC_Pay.setOnMouseClicked(event -> handlerBtnC_PayAction(event));
		// <������� ������� �̳� -> �ϳ� > ���̺� Ŭ�� �̺�Ʈ ó��
		CoTableView.setOnMousePressed(event -> handlerCoTableViewAction(event));
		// �����Ȳ���� ������� ��������� ������ư
		btnC_Delete.setOnMouseClicked(event -> handlerBtnC_DeleteAction(event));
		// ù��° �ǿ��� ���뿩�� ������ ��쿡 �뿩 ������ �Ѿ�鼭
		btnRental.setOnMouseClicked(event -> handlerBtnRentalAction(event));
		// �뿩�ǿ��� �뿩����Ʈ ��ü����
		btnR_TotalList.setOnAction(event -> handlerBtnR_TotalListAction(event));
		// �뿩�ǿ��� �����=�뿩�� �˻��ϱ�
		btnR_Search.setOnAction(event -> handlerBtnR_SearchAction(event));
		// �뿩���̺� Ŭ���̺�Ʈ
		RentalView.setOnMousePressed(event -> handlerRentalViewAction(event));
		// ���뿩 �Ѿ� ���� ��ư�̺�Ʈ
		btnR_Total.setOnAction(event -> handlerBtnR_TotalAction(event));
		// ���뿩��Ϲ�ư�������� �뿩����׸��Է°��� �̺�Ʈ
		btnR_Edit.setOnAction(event -> handlerBtnR_EditAction(event));
		// �뿩�� ������ �� �뿩����׸� ��Ʈ�� off
		RentalTab.setOnSelectionChanged(event -> handlerRentalTabAction(event));
		// �뿩�ǿ��� ����Ʈ ������ư �̺�Ʈ
		btnR_Delete.setOnMouseClicked(event -> handlerBtnR_DeleteAction(event));
		// �뿩�ǿ��� ������ �Ϸ��ư �̺�Ʈ
		btnR_Pay.setOnMouseClicked(event -> handlerBtnR_PayAction(event));
		// �뿩�ǿ��� ���ݳ� �Ϸ��ư �̺�Ʈ
		btnR_Return.setOnMouseClicked(event -> handlerBtnR_ReturnAction(event));
		// �뿩�ǿ��� ��� ��ư �̺�Ʈ
		btnR_Ok.setOnMouseClicked(event -> handlerBtnR_OkAction(event));
		// �뿩�ǿ��� ��Ͻ� �ʱ�ȭ ��ư
		btnR_Init.setOnAction(event -> handlerBtnR_InitAction(event));

		// -----------------------------------------------------------------------------------------------------------------
	}

	// ��� �Ѿ� ����
	public void handlerBtnC_TotalAction(ActionEvent event) {

		// ���� ��ư���� ������ ���ð� ������ �������� ���� �ʼ� �Է�
		int basePrice = 0;
		if (rbMae.isSelected() && !txtD_day.getText().equals("")) {
			basePrice = Integer.parseInt(txtD_day.getText()) * 150000;
		} else if (rbSo.isSelected() && !txtD_day.getText().equals("")) {
			basePrice = Integer.parseInt(txtD_day.getText()) * 180000;
		} else if (rbDe.isSelected() && !txtD_day.getText().equals("")) {
			basePrice = Integer.parseInt(txtD_day.getText()) * 400000;
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("������ ���� ����");
			alert.setHeaderText("������� �����ϼ��� �� �Է��Ͻÿ�.");
			alert.showAndWait();
			return;
		}
		// �迭�� üũ�ڽ� üũ�ÿ� ������ �� ���ǿ����
		int[] partPrice = new int[6];
		for (int i = 0; i < partPrice.length; i++) {
			partPrice[i] = 0;
		}
		if (chMorning1.isSelected() && !txtMorning1.getText().equals("")) {
			partPrice[0] = Integer.parseInt(txtMorning1.getText()) * 30000;
		} else if (chMorning1.isSelected() && txtMorning1.getText().equals("")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���� �Է� ����");
			alert.setHeaderText("������� �����ϼ��� �� �Է��Ͻÿ�.");
			alert.showAndWait();
			return;

		}

		if (chAfternoon1.isSelected() && !txtAfternoon1.getText().equals("")) {
			partPrice[1] = Integer.parseInt(txtAfternoon1.getText()) * 40000;
		} else if (chAfternoon1.isSelected() && txtAfternoon1.getText().equals("")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���� �Է� ����");
			alert.setHeaderText("������� �����ϼ��� �� �Է��Ͻÿ�.");
			alert.showAndWait();
			return;

		}

		if (chNight1.isSelected() && !txtNight1.getText().equals("")) {
			partPrice[2] = Integer.parseInt(txtNight1.getText()) * 45000;
		} else if (chNight1.isSelected() && txtNight1.getText().equals("")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���� �Է� ����");
			alert.setHeaderText("������� �����ϼ��� �� �Է��Ͻÿ�.");
			alert.showAndWait();
			return;

		}

		if (chMorning2.isSelected() && !txtMorning2.getText().equals("")) {
			partPrice[3] = Integer.parseInt(txtMorning2.getText()) * 30000;
		} else if (chMorning2.isSelected() && txtMorning2.getText().equals("")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���� �Է� ����");
			alert.setHeaderText("������� �����ϼ��� �� �Է��Ͻÿ�.");
			alert.showAndWait();
			return;

		}
		if (chAfternoon2.isSelected() && !txtAfternoon2.getText().equals("")) {
			partPrice[4] = Integer.parseInt(txtAfternoon2.getText()) * 40000;
		} else if (chAfternoon2.isSelected() && txtAfternoon2.getText().equals("")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���� �Է� ����");
			alert.setHeaderText("������� �����ϼ��� �� �Է��Ͻÿ�.");
			alert.showAndWait();
			return;

		}
		if (chNight2.isSelected() && !txtNight2.getText().equals("")) {
			partPrice[5] = Integer.parseInt(txtNight2.getText()) * 45000;
		} else if (chNight2.isSelected() && txtNight2.getText().equals("")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���� �Է� ����");
			alert.setHeaderText("������� �����ϼ��� �� �Է��Ͻÿ�.");
			alert.showAndWait();
			return;

		}

		int c_Total = basePrice;
		for (int i = 0; i < partPrice.length; i++) {
			c_Total = c_Total + partPrice[i];
		}
		System.out.println(c_Total);
		txtC_Total.setText(c_Total + "");

	}

	// ������̸����� ��ϵ� ��������� �˻��̺�Ʈ
	public void handlerBtnSearchAction(ActionEvent event) {
		sVo = new SuperlativesVO();
		SuperlativesDAO sDao = new SuperlativesDAO();

		Object[][] totalData = null;

		String searchP_Name = "";
		boolean searchResult = false;

		try {
			searchP_Name = txtSearch.getText().trim();
			sDao = new SuperlativesDAO();
			sVo = sDao.getSuperlativesCheck(searchP_Name);

			if (searchP_Name.equals("")) {
				System.out.println("��");
				searchResult = true;
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("����� ���� �˻�");
				alert.setHeaderText("��ϵ� ������� �̸��� �Է��Ͻÿ�.");
				alert.showAndWait();

			}
			if (!searchP_Name.equals("") && (sVo != null)) {
				System.out.println("��");
				ArrayList<String> title;
				ArrayList<SuperlativesVO> list;

				title = sDao.getColumnName();
				int columnCount = title.size();

				list = sDao.getSuperlativesTotal();
				int rowCount = list.size();

				totalData = new Object[rowCount][columnCount];

				if (sVo.getP_Name().equals(searchP_Name)) {
					txtSearch.clear();
					data.removeAll(data);

					for (int index = 0; index < rowCount; index++) {
						sVo = list.get(index);

						if (sVo.getP_Name().equals(searchP_Name)) {
							data.add(sVo);
							searchResult = true;
						}

					}
				}
			}

			if (!searchResult) {
				txtSearch.clear();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("����� ���� �˻�");
				alert.setHeaderText(searchP_Name + "���� ����� �Ǿ����� �ʽ��ϴ�.");
				alert.setContentText("��ϵ� ����ڷ� �ٽ� �˻��Ͻÿ�.");
				alert.showAndWait();

			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("����� ���� �˻� ����");
			alert.setHeaderText("����� ���� �˻��� ������ �߻��Ͽ����ϴ�.");
			alert.setContentText("�ٽ� �Է��Ͻÿ�.");
			alert.showAndWait();
		}
	}

	// ����� ��ü����Ʈ
	public void totalList() {
		Object[][] totalData;

		SuperlativesDAO sDao = new SuperlativesDAO();
		SuperlativesVO sVo = new SuperlativesVO();
		ArrayList<String> title;
		ArrayList<SuperlativesVO> list;

		title = sDao.getColumnName();
		int columnCount = title.size();

		list = sDao.getSuperlativesTotal();
		int rowCount = list.size();

		for (int index = 0; index < rowCount; index++) {
			sVo = list.get(index);

			data.addAll(sVo);
		}

	}

	// ������� ��� ��ü����Ʈ
	private void totalList1() {
		CoronationDAO cDao = new CoronationDAO();
		CoronationVO cVo2 = new CoronationVO();
		ArrayList<CoronationVO> list;
		list = cDao.getCoronativesTotal();
		int rowCount = list.size();
		CoData.removeAll(CoData);
		for (int index = 0; index < rowCount; index++) {
			cVo2 = list.get(index);
			System.out.println(cVo2.getC_No());
			CoData.add(cVo2);
		}

	}

	// ������� �뿩 ��ü����Ʈ
	private void totalList2() {
		RentalDAO rDao = new RentalDAO();
		RentalVO rVo = new RentalVO();
		ArrayList<RentalVO> list;
		list = rDao.getRentalTotal();
		int rowCount = list.size();
		ReData.removeAll(ReData);
		for (int index = 0; index < rowCount; index++) {
			rVo = list.get(index);

			ReData.add(rVo);
		}

	}

	// ���̺� Ŭ�� �̺�Ʈ
	public void handlertableViewAction(MouseEvent event) {
		selectSuperlativesVO = tableView.getSelectionModel().getSelectedItems();
		selectedIndex = tableView.getSelectionModel().getSelectedIndex();

		String phone = selectSuperlativesVO.get(0).getContactAll();

		String[] array = phone.split("-");
		dumpArray(array);

		try {
			txtP_Name.setText(selectSuperlativesVO.get(0).getP_Name());
			txtContact1.setText(array[1]);
			txtContact2.setText(array[2]);
			cbContact.setValue(array[0]);
			System.out.println();

			txtGroupName.setText(selectSuperlativesVO.get(0).getGroupName());
			txtTitle.setText(selectSuperlativesVO.get(0).getTitle());
			txtAddress.setText(selectSuperlativesVO.get(0).getAddress());
			txtPrize.setText(selectSuperlativesVO.get(0).getPrize());
			btnPOk.setDisable(true);
			btnEdit.setDisable(false);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("����� ó��");
			alert.setHeaderText("������ ����ڷ� ������ �۾��� �����Ͻÿ�." + "");
			alert.showAndWait();
		} catch (Exception e) {

		}

	}

	// �迭�� ȭ�鿡, ��Һ��� �˱� ���� ���
	public static void dumpArray(String[] array) {
		for (int i = 0; i < array.length; i++)
			System.out.format("array[%d] = %s\n", i, array[i]);
	}

	// ����� ���� �̺�Ʈ ó��
	public void handlerBtnEditAction(ActionEvent event) {
		try {
			if (event.getSource().equals(btnEdit)) {

				SuperlativesVO sVo = new SuperlativesVO();
				SuperlativesDAO sDao = new SuperlativesDAO();

				data.remove(selectedIndex);
				sVo = new SuperlativesVO(txtP_Name.getText(), txtGroupName.getText(), txtTitle.getText(),
						txtAddress.getText(),
						((cbContact.getValue() + "-" + txtContact1.getText() + "-" + txtContact2.getText())),

						txtPrize.getText());

				sDao.getSuperlativesUpdate(sVo);

				data.removeAll(data);
				totalList();

			}

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("����� ���� ���� ����");
			alert.setHeaderText("����� ���� ������ ������ �߻��Ͽ����ϴ�.");
			alert.setContentText("�ٽ� �Է��Ͻÿ�.");
			alert.showAndWait();

		}

	}

	// ��������Է� ��ư �����̺�Ʈ
	public void handlerBtnC_InfoAction(ActionEvent event) {
		rbMae.setDisable(false);
		rbSo.setDisable(false);
		rbDe.setDisable(false);
		dpStart.setDisable(false);
		dpEnd.setDisable(false);
		chMorning1.setDisable(false);
		chAfternoon1.setDisable(false);
		chNight1.setDisable(false);
		chMorning2.setDisable(false);
		chAfternoon2.setDisable(false);
		chNight2.setDisable(false);
		txtMorning1.setEditable(true);
		txtAfternoon1.setEditable(true);
		txtNight1.setEditable(true);
		txtMorning2.setEditable(true);
		txtAfternoon2.setEditable(true);
		txtNight2.setEditable(true);
		txtD_day.setEditable(true);
		btnC_Total.setDisable(false);
		txtC_Total.setEditable(true);
		btnOk.setDisable(false);
		btnEdit.setDisable(true);

	}

	// ����� ���� ���� �̺�Ʈ ó��
	public void handlerBtnDeleteAction(MouseEvent event) {
		// ����� ������ư
		SuperlativesDAO sDao = null;

		sDao = new SuperlativesDAO();

		try {
			String name = tableView.getSelectionModel().getSelectedItem().getP_Name();
			sDao.getSuperlativesDelete(name);
			data.removeAll(data);

			// ����� ��ü ����
			totalList();

			// ��ư ����
			btnEdit.setDisable(true);
			// �ؽ�Ʈ �ʵ� ����
			txtP_Name.clear();
			txtGroupName.clear();
			txtTitle.clear();
			cbContact.getSelectionModel().clearSelection();
			txtContact1.clear();
			txtContact2.clear();
			txtPrize.clear();
			txtAddress.clear();

		} catch (Exception e) {
			e.printStackTrace();
		}
		editDelete = true;
	}

	// ������� ������� ����̺�Ʈ
	public void handlerBtnOkAction(ActionEvent event) {
		try {
			CoData.removeAll(CoData);
			CoronationVO cVo = null;
			CoronationDAO cDao = new CoronationDAO();

			if (event.getSource().equals(btnOk)) {
				cVo = new CoronationVO(txtP_Name.getText(), hallGroup.getSelectedToggle().getUserData().toString(),
						Date.valueOf(dpStart.getValue()), Date.valueOf(dpEnd.getValue()),
						Integer.parseInt(txtC_Total.getText()), "��  ��");
				cDao = new CoronationDAO();
				cDao.getCoronationInfo(cVo);

				if (cDao != null) {
					totalList1();

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("������� ���");
					alert.setHeaderText(txtP_Name.getText() + "���� ����������� ��ϵǾ����ϴ�.");

				}

			}
		} catch (Exception ie) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("������� ���");
			alert.setHeaderText("��������� ��Ȯ�� �Է��Ͻÿ�.");
			alert.showAndWait();
			ie.printStackTrace();
		}

	}

	// �����Ȳ���� ����� �˻���ư �̺�Ʈ
	public void handlerBtnC_SearchAction(ActionEvent event) {
		CoronationVO cVo = new CoronationVO();
		CoronationDAO cDao = null;

		Object[][] totalData = null;

		String searchP_Name = "";
		boolean searchResult = false;

		try {

			searchP_Name = txtC_Search.getText().trim();
			cDao = new CoronationDAO();
			cVo = cDao.getCoronationCheck(searchP_Name);

			if (searchP_Name.equals("")) {
				searchResult = true;
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("�����Ȳ �˻�");
				alert.setHeaderText("����ڸ��� �Է��Ͻÿ�.");
				alert.showAndWait();
			}

			if (!searchP_Name.equals("") && (cVo != null)) {
				ArrayList<String> title;
				ArrayList<CoronationVO> list;

				title = cDao.getColumnName();
				int columnCount = title.size();

				list = cDao.getCoronativesTotal();
				int rowCount = list.size();

				totalData = new Object[rowCount][columnCount];

				if (cVo.getP_Name().equals(searchP_Name)) {
					txtC_Search.clear();
					CoData.removeAll(CoData);
					for (int index = 0; index < rowCount; index++) {
						cVo = list.get(index);
						if (cVo.getP_Name().equals(searchP_Name)) {
							CoData.add(cVo);
							searchResult = true;
						}
					}
				}
			}
			if (!searchResult) {
				txtC_Search.clear();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("�����Ȳ �˻�");
				alert.setHeaderText(searchP_Name + "���� ����Ʈ�� �����ϴ�.");
				alert.setContentText("�ٽ� �˻��ϼ���.");
				alert.showAndWait();
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("�����Ȳ �˻� ����");
			alert.setHeaderText("�����Ȳ �˻��� ������ �߻��Ͽ����ϴ�.");
			alert.setContentText("����ڸ����� �ٽ� �Է��Ͻÿ�.");
			alert.showAndWait();
		}
	}

	// ������ ��� ���� �ؽ�Ʈ�ʵ� �ʱ�ȭ ��ư �̺�Ʈ
	public void handlerBtnInitAction(ActionEvent event) {
		init();
	}

	// (������ ��� ���� �ʱ�ȭ ��ư)-> �ʱ�ȭ �޼ҵ�
	public void init() {
		txtP_Name.clear();
		txtGroupName.clear();
		txtTitle.clear();
		cbContact.getSelectionModel().clearSelection();
		txtContact1.clear();
		txtContact2.clear();
		txtPrize.clear();
		txtAddress.clear();
		hallGroup.selectToggle(null);
		chMorning1.setSelected(false);
		chAfternoon1.setSelected(false);
		chNight1.setSelected(false);
		chMorning2.setSelected(false);
		chAfternoon2.setSelected(false);
		chNight2.setSelected(false);
		txtMorning1.clear();
		txtAfternoon1.clear();
		txtNight1.clear();
		txtMorning2.clear();
		txtAfternoon2.clear();
		txtNight2.clear();
		txtD_day.clear();
		txtC_Total.clear();

		dpStart.setValue(null);
		dpEnd.setValue(null);
		rbMae.setDisable(true);
		rbSo.setDisable(true);
		rbDe.setDisable(true);
		dpStart.setDisable(true);
		dpEnd.setDisable(true);
		chMorning1.setDisable(true);
		chAfternoon1.setDisable(true);
		chNight1.setDisable(true);
		chMorning2.setDisable(true);
		chAfternoon2.setDisable(true);
		chNight2.setDisable(true);
		txtMorning1.setEditable(false);
		txtAfternoon1.setEditable(false);
		txtNight1.setEditable(false);
		txtMorning2.setEditable(false);
		txtAfternoon2.setEditable(false);
		txtNight2.setEditable(false);
		txtD_day.setEditable(false);
		btnC_Total.setDisable(true);
		txtC_Total.setEditable(false);
		btnOk.setDisable(true);
		btnEdit.setDisable(true);
		btnPOk.setDisable(false);
		btnInit.setDisable(true);

	}

	// ������� �̳����� �ϳ����� �����ϴ� ��ư
	public void handlerBtnC_PayAction(MouseEvent event) {
		try {
			int c_Num = CoTableView.getSelectionModel().getSelectedItem().getC_No();

			CoronationDAO cDao = new CoronationDAO();
			CoronationVO cVo = new CoronationVO();

			cVo = cDao.getCoronationPaysearch(c_Num);

			if (cVo.getC_PayMent().equals("��  ��")) {
				cDao.getCoronationPay(c_Num);

				CoData.removeAll(CoData);
				totalList1();
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("����Ѿ� ���� �ȳ�");
				alert.setHeaderText("�̹� �ϳ��Ǿ����ϴ�.");
				alert.showAndWait();
			}

		} catch (Exception e) {
			e.printStackTrace();

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("����Ѿ� ���� �ȳ�");
			alert.setHeaderText("����Ѿ� �ϳ� ���濡 �����Ͽ����ϴ�.");
			alert.showAndWait();
		}

	}

	// ������̺� Ŭ�� �̺�Ʈ ó��
	public void handlerCoTableViewAction(MouseEvent event) {
		selectCoronationVO = CoTableView.getSelectionModel().getSelectedItems();
		selectedIndex = CoTableView.getSelectionModel().getSelectedIndex();

		/*
		 * btnC_Delete.setDisable(false); CoData_d =
		 * CoTableView.getSelectionModel().getSelectedItem();
		 * 
		 * no = data.get(0).get
		 */

		/*
		 * txtP_Name.setText(selectCoronationVO.get(0).getP_Name());
		 * hallGroup.setUserData(selectCoronationVO.get(0).getHall());
		 * dpStart.setUserData(selectCoronationVO.get(0).getStart_Date());
		 * dpEnd.setUserData(selectCoronationVO.get(0).getEnd_Date());
		 * txtC_Total.setText(selectCoronationVO.get(0).getC_Total() + "");
		 */
	}

	// �����Ȳ���� ������� ��������� ������ư
	public void handlerBtnC_DeleteAction(MouseEvent event) {

		CoronationDAO cDao = null;
		cDao = new CoronationDAO();

		try {
			if (event.getClickCount() != 2) {
				int c_num = CoTableView.getSelectionModel().getSelectedItem().getC_No();
				System.out.println(c_num);
				cDao.getCoronationDelete(c_num);
				CoData.removeAll(CoData);
				// ����� ��ü ����
				totalList1();
				// ��ư ����
				btnEdit.setDisable(true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// editDelete = true;

	}

	// ���뿩��û��ư�̺�Ʈ
	public void handlerBtnRentalAction(MouseEvent event) {

		RentalVO rVo = null;
		RentalDAO rDao = new RentalDAO();

		if (event.getSource().equals(btnRental)) {
			int rp = Integer.parseInt(txtFloor_P.getText());
			int rp1 = Integer.parseInt(txtLodder_P.getText());
			int rp2 = Integer.parseInt(txtCloth_P.getText());
			int rp3 = Integer.parseInt(txtFog_P.getText());
			int rp4 = Integer.parseInt(txtCanterbury_P.getText());
			int rp5 = Integer.parseInt(txtLConsol_P.getText());
			int rp6 = Integer.parseInt(txtPar_64_P.getText());
			int rp7 = Integer.parseInt(txtElip_P.getText());
			int rp8 = Integer.parseInt(txtMoving_P.getText());
			int rp9 = Integer.parseInt(txtPar_32_P.getText());
			int rp10 = Integer.parseInt(txtLaser_P.getText());
			int rp11 = Integer.parseInt(txtMConsol_P.getText());
			int rp12 = Integer.parseInt(txtCmic_P.getText());
			int rp13 = Integer.parseInt(txtSpeaker_P.getText());
			int rp14 = Integer.parseInt(txtWoofer_P.getText());
			int rp15 = Integer.parseInt(txtWmic_P.getText());
			int rp16 = Integer.parseInt(txtPwAmp_P.getText());

			rVo = new RentalVO(CoTableView.getSelectionModel().getSelectedItem().getC_No(),
					CoTableView.getSelectionModel().getSelectedItem().getP_Name(),
					Date.valueOf(dpStart.getValue()),
					Date.valueOf(dpEnd.getValue()), 0, 0, 0, rp + rp1 + rp2 + rp3 + rp4 + rp5 + rp6 + rp7 + rp8 + rp9
							+ rp10 + rp11 + rp12 + rp13 + rp14 + rp15 + rp16,
					Integer.parseInt(txtR_Total.getText()), "�� ��", "�� ��");
			rDao = new RentalDAO();

			try {
				rDao.getRentalInfo(rVo);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (rDao != null) {
				ReData.removeAll(ReData);
				totalList2();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("�뿩���� ��û");
				alert.setHeaderText(txtP_Name.getText() + " ���뿩 ��û�� �Ϸ�Ǿ����ϴ�.");
				alert.setContentText("�뿩 ���� ���� �뿩�� ��� �����Ͻÿ�.");

				// tabpane.getSelectionModel().selectedItemProperty().
			}
		}

	}

	// �뿩�ǿ��� �뿩����Ʈ ��ü����
	public void handlerBtnR_TotalListAction(ActionEvent event) {

		try {
			ReData.removeAll(ReData);

			// �����Ȳ ��ü ����
			totalList2();
		} catch (Exception e) {

		}
	}

	// �뿩�ǿ��� �����=�뿩�� �˻���ư �̺�Ʈ
	public void handlerBtnR_SearchAction(ActionEvent event) {
		RentalVO rVo = new RentalVO();
		RentalDAO rDao = null;

		Object[][] totalData = null;

		String searchP_Name = "";
		boolean searchResult = false;

		try {

			searchP_Name = txtR_Search.getText().trim();
			rDao = new RentalDAO();
			rVo = rDao.getRentalCheck(searchP_Name);

			if (searchP_Name.equals("")) {
				searchResult = true;
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("�뿩��Ȳ �˻�");

			}
			if (!searchP_Name.equals("") && (rVo != null)) {
				ArrayList<String> title;
				ArrayList<RentalVO> list;

				title = rDao.getColumnName();
				int columnCount = title.size();

				list = rDao.getRentalTotal();
				int rowCount = list.size();

				totalData = new Object[rowCount][columnCount];

				if (rVo.getP_Name().equals(searchP_Name)) {
					txtR_Search.clear();
					ReData.removeAll(ReData);

					for (int index = 0; index < rowCount; index++) {
						rVo = list.get(index);

						if (rVo.getP_Name().equals(searchP_Name)) {
							ReData.add(rVo);
							searchResult = true;
						}
					}
				}
			}
			if (!searchResult) {
				txtR_Search.clear();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("�뿩��Ȳ �˻�");
				alert.setHeaderText(searchP_Name + "���� ����Ʈ�� �����ϴ�.");
				alert.setContentText("�ٽ� �˻��ϼ���.");
				alert.showAndWait();

			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("�뿩��Ȳ �˻� ����");
			alert.setHeaderText("�뿩��Ȳ �˻��� ������ �߻��Ͽ����ϴ�.");
			alert.setContentText("�����(�뿩��)������ �ٽ� �Է��Ͻÿ�.");
			alert.showAndWait();

		}

	}

	// ������̺�� Ŭ���̺�Ʈ
	public void handlerRentalViewAction(MouseEvent event) {
		selectRentalVO = RentalView.getSelectionModel().getSelectedItems();
		//selectedIndex = RentalView.getSelectionModel().getSelectedIndex();
		
		txtR_Name.setText(selectRentalVO.get(0).getP_Name());
		dpR_Start.setValue(selectRentalVO.get(0).getRental_StartDate().toLocalDate());
		dpR_End.setValue(selectRentalVO.get(0).getRental_EndDate().toLocalDate());

	}

	// �뿩 �Ѿ� ����
	public void handlerBtnR_TotalAction(ActionEvent event) {

		int[] price = new int[18];

		for (int i = 0; i < price.length; i++) {
			price[i] = 0;
		}
		// ��ġ �� �ü� �����
		if (chFloor.isSelected() && !txtFloor_P.getText().equals("0")) {
			price[0] = Integer.parseInt(txtFloor_P.getText()) * 30000;
		} else if (chFloor.isSelected() && txtFloor_P.getText().equals("0")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���� �Է� ����");
			alert.setHeaderText("������ �׸��� ������ �� �Է��Ͻÿ�.");
			alert.showAndWait();

			return;
		}

		if (chLodder.isSelected() && !txtLodder_P.getText().equals("0")) {
			price[1] = Integer.parseInt(txtLodder_P.getText()) * 10000;
		} else if (chLodder.isSelected() && txtLodder_P.getText().equals("0")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���� �Է� ����");
			alert.setHeaderText("������ �׸��� ������ �� �Է��Ͻÿ�.");
			alert.showAndWait();

			return;
		}

		if (chCloth.isSelected() && !txtCloth_P.getText().equals("0")) {
			price[2] = Integer.parseInt(txtCloth_P.getText()) * 15000;
		} else if (chCloth.isSelected() && txtCloth_P.getText().equals("0")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���� �Է� ����");
			alert.setHeaderText("������ �׸��� ������ �� �Է��Ͻÿ�.");
			alert.showAndWait();

			return;
		}

		if (chFog.isSelected() && !txtFog_P.getText().equals("0")) {
			price[3] = Integer.parseInt(txtFog_P.getText()) * 20000;
		} else if (chFog.isSelected() && txtFog_P.getText().equals("0")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���� �Է� ����");
			alert.setHeaderText("������ �׸��� ������ �� �Է��Ͻÿ�.");
			alert.showAndWait();

			return;
		}

		if (chCanterbury.isSelected() && !txtCanterbury_P.getText().equals("0")) {
			price[4] = Integer.parseInt(txtCanterbury_P.getText()) * 1000;
		} else if (chCanterbury.isSelected() && txtCanterbury_P.getText().equals("0")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���� �Է� ����");
			alert.setHeaderText("������ �׸��� ������ �� �Է��Ͻÿ�.");
			alert.showAndWait();

			return;
		}

		if (chCool.isSelected()) {
			price[5] = 50000;
		} else if (chCool.isSelected()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���� �Է� ����");
			alert.setHeaderText("������ �׸��� ������ �� �Է��Ͻÿ�.");
			alert.showAndWait();

			return;
		}

		if (chHeat.isSelected()) {
			price[6] = 30000;
		} else if (chHeat.isSelected()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���� �Է� ����");
			alert.setHeaderText("������ �׸��� ������ �� �Է��Ͻÿ�.");
			alert.showAndWait();

			return;
		}

		// ����� �����
		if (chLConsol.isSelected() && !txtLConsol_P.getText().equals("0")) {
			price[7] = Integer.parseInt(txtLConsol_P.getText()) * 40000;
		} else if (chLConsol.isSelected() && txtLConsol_P.getText().equals("0")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���� �Է� ����");
			alert.setHeaderText("������ �׸��� ������ �� �Է��Ͻÿ�.");
			alert.showAndWait();

			return;
		}

		if (chPar_64.isSelected() && !txtPar_64_P.getText().equals("0")) {
			price[8] = Integer.parseInt(txtPar_64_P.getText()) * 3000;
		} else if (chPar_64.isSelected() && txtPar_64_P.getText().equals("0")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���� �Է� ����");
			alert.setHeaderText("������ �׸��� ������ �� �Է��Ͻÿ�.");
			alert.showAndWait();

			return;
		}

		if (chElip.isSelected() && !txtElip_P.getText().equals("0")) {
			price[9] = Integer.parseInt(txtElip_P.getText()) * 5000;
		} else if (chElip.isSelected() && txtElip_P.getText().equals("0")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���� �Է� ����");
			alert.setHeaderText("������ �׸��� ������ �� �Է��Ͻÿ�.");
			alert.showAndWait();

			return;
		}

		if (chMoving.isSelected() && !txtMoving_P.getText().equals("0")) {
			price[10] = Integer.parseInt(txtMoving_P.getText()) * 20000;
		} else if (chMoving.isSelected() && txtMoving_P.getText().equals("0")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���� �Է� ����");
			alert.setHeaderText("������ �׸��� ������ �� �Է��Ͻÿ�.");
			alert.showAndWait();

			return;
		}

		if (chPar_32.isSelected() && !txtPar_32_P.getText().equals("0")) {
			price[11] = Integer.parseInt(txtPar_32_P.getText()) * 5500;
		} else if (chPar_32.isSelected() && txtPar_32_P.getText().equals("0")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���� �Է� ����");
			alert.setHeaderText("������ �׸��� ������ �� �Է��Ͻÿ�.");
			alert.showAndWait();

			return;
		}

		if (chLaser.isSelected() && !txtLaser_P.getText().equals("0")) {
			price[12] = Integer.parseInt(txtLaser_P.getText()) * 150000;
		} else if (chLaser.isSelected() && txtLaser_P.getText().equals("0")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���� �Է� ����");
			alert.setHeaderText("������ �׸��� ������ �� �Է��Ͻÿ�.");
			alert.showAndWait();

			return;
		}

		// ����� �����
		if (chMConsol.isSelected() && !txtMConsol_P.getText().equals("")) {
			price[13] = Integer.parseInt(txtMConsol_P.getText()) * 40000;
		} else if (chMConsol.isSelected() && txtMConsol_P.getText().equals("0")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���� �Է� ����");
			alert.setHeaderText("������ �׸��� ������ �� �Է��Ͻÿ�.");
			alert.showAndWait();

			return;
		}

		if (chCmic.isSelected() && !txtCmic_P.getText().equals("0")) {
			price[14] = Integer.parseInt(txtCmic_P.getText()) * 5000;
		} else if (chCmic.isSelected() && txtCmic_P.getText().equals("0")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���� �Է� ����");
			alert.setHeaderText("������ �׸��� ������ �� �Է��Ͻÿ�.");
			alert.showAndWait();

			return;
		}

		if (chSpeaker.isSelected() && !txtSpeaker_P.getText().equals("0")) {
			price[15] = Integer.parseInt(txtSpeaker_P.getText()) * 23000;
		} else if (chSpeaker.isSelected() && txtSpeaker_P.getText().equals("0")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���� �Է� ����");
			alert.setHeaderText("������ �׸��� ������ �� �Է��Ͻÿ�.");
			alert.showAndWait();

			return;
		}

		if (chWoofer.isSelected() && !txtWoofer_P.getText().equals("0")) {
			price[16] = Integer.parseInt(txtWoofer_P.getText()) * 20000;
		} else if (chWoofer.isSelected() && txtWoofer_P.getText().equals("0")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���� �Է� ����");
			alert.setHeaderText("������ �׸��� ������ �� �Է��Ͻÿ�.");
			alert.showAndWait();

			return;
		}

		if (chWmic.isSelected() && !txtWmic_P.getText().equals("0")) {
			price[17] = Integer.parseInt(txtWmic_P.getText()) * 10000;
		} else if (chWmic.isSelected() && txtWmic_P.getText().equals("0")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���� �Է� ����");
			alert.setHeaderText("������ �׸��� ������ �� �Է��Ͻÿ�.");
			alert.showAndWait();

			return;
		}

		if (chPwAmp.isSelected() && !txtPwAmp_P.getText().equals("0")) {
			price[18] = Integer.parseInt(txtPwAmp_P.getText()) * 20000;
		} else if (chPwAmp.isSelected() && txtPwAmp_P.getText().equals("0")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���� �Է� ����");
			alert.setHeaderText("������ �׸��� ������ �� �Է��Ͻÿ�.");
			alert.showAndWait();

			return;
		}
		int r_Total = 0;
		for (int i = 0; i < price.length; i++) {
			r_Total += price[i];
		}

		System.out.println(r_Total);
		txtR_Total.setText(r_Total + "");

	}

	// ���뿩��Ϲ�ư�������� �뿩����׸��Է°��� �̺�Ʈ
	public void handlerBtnR_EditAction(ActionEvent event) {
		txtR_Search.setEditable(true);
		dpR_Start.setDisable(false);
		dpR_End.setDisable(false);
		chFloor.setDisable(false);
		chLodder.setDisable(false);
		chCloth.setDisable(false);
		chFog.setDisable(false);
		chCanterbury.setDisable(false);
		chCool.setDisable(false);
		chHeat.setDisable(false);

		chLConsol.setDisable(false);
		chPar_64.setDisable(false);
		chElip.setDisable(false);
		chMoving.setDisable(false);
		chPar_32.setDisable(false);
		chLaser.setDisable(false);

		chMConsol.setDisable(false);
		chCmic.setDisable(false);
		chSpeaker.setDisable(false);
		chWoofer.setDisable(false);
		chWmic.setDisable(false);
		chPwAmp.setDisable(false);

		txtFloor_P.setEditable(true);
		txtLodder_P.setEditable(true);
		txtCloth_P.setEditable(true);
		txtFog_P.setEditable(true);
		txtCanterbury_P.setEditable(true);

		txtLConsol_P.setEditable(true);
		txtPar_64_P.setEditable(true);
		txtElip_P.setEditable(true);
		txtMoving_P.setEditable(true);
		txtPar_32_P.setEditable(true);
		txtLaser_P.setEditable(true);

		txtMConsol_P.setEditable(true);
		txtCmic_P.setEditable(true);
		txtSpeaker_P.setEditable(true);
		txtWoofer_P.setEditable(true);
		txtWmic_P.setEditable(true);
		txtPwAmp_P.setEditable(true);

		btnR_Total.setDisable(false);
		txtR_Total.setEditable(true);
		btnR_Ok.setDisable(false);
		btnR_Init.setDisable(false);

	}

	// �뿩�� ������ �� �뿩����׸� ��Ʈ�� off
	public void handlerRentalTabAction(Event event) {
		txtR_Search.setEditable(false);
		dpR_Start.setDisable(true);
		dpR_End.setDisable(true);
		chFloor.setDisable(true);
		chLodder.setDisable(true);
		chCloth.setDisable(true);
		chFog.setDisable(true);
		chCanterbury.setDisable(true);
		chCool.setDisable(true);
		chHeat.setDisable(true);

		chLConsol.setDisable(true);
		chPar_64.setDisable(true);
		chElip.setDisable(true);
		chMoving.setDisable(true);
		chPar_32.setDisable(true);
		chLaser.setDisable(true);

		chMConsol.setDisable(true);
		chCmic.setDisable(true);
		chSpeaker.setDisable(true);
		chWoofer.setDisable(true);
		chWmic.setDisable(true);
		chPwAmp.setDisable(true);

		txtFloor_P.setEditable(false);
		txtLodder_P.setEditable(false);
		txtCloth_P.setEditable(false);
		txtFog_P.setEditable(false);
		txtCanterbury_P.setEditable(false);

		txtLConsol_P.setEditable(false);
		txtPar_64_P.setEditable(false);
		txtElip_P.setEditable(false);
		txtMoving_P.setEditable(false);
		txtPar_32_P.setEditable(false);
		txtLaser_P.setEditable(false);

		txtMConsol_P.setEditable(false);
		txtCmic_P.setEditable(false);
		txtSpeaker_P.setEditable(false);
		txtWoofer_P.setEditable(false);
		txtWmic_P.setEditable(false);
		txtPwAmp_P.setEditable(false);

		btnR_Total.setDisable(true);
		txtR_Total.setEditable(false);
		btnR_Ok.setDisable(true);
		btnR_Init.setDisable(true);

	}

	// �뿩�ǿ��� ����Ʈ ������ư �̺�Ʈ
	public void handlerBtnR_DeleteAction(MouseEvent event) {
		RentalDAO rDao = null;
		rDao = new RentalDAO();

		try {
			if (event.getClickCount() != 2) {
				int rno = RentalView.getSelectionModel().getSelectedItem().getR_No();
				System.out.println(rno);
				rDao.getRentalDelete(rno);
				ReData.removeAll(ReData);
				// ����� ��ü ����
				totalList2();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// �뿩�ǿ��� ������ �Ϸ��ư �̺�Ʈ
	public void handlerBtnR_PayAction(MouseEvent event) {
		try {
			int r_Num = RentalView.getSelectionModel().getSelectedItem().getR_No();

			RentalDAO rDao = new RentalDAO();
			RentalVO rVo = new RentalVO();

			rVo = rDao.getRentalPaysearch(r_Num);

			if (rVo.getRental_PayMent().equals("�� ��")) {
				rDao.getRentalPay(r_Num);

				ReData.removeAll(ReData);
				totalList2();
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("���뿩�Ѿ� ���� �ȳ�");
				alert.setHeaderText("�̹� �ϳ��Ǿ����ϴ�.");
				alert.showAndWait();
			}

		} catch (Exception e) {
			e.printStackTrace();

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���뿩 ���� �ȳ�");
			alert.setHeaderText("���뿩�Ѿ� �ϳ� ���濡 �����Ͽ����ϴ�.");
			alert.showAndWait();
		}
	}

	// �뿩�ǿ��� ���ݳ� �Ϸ��ư �̺�Ʈ
	public void handlerBtnR_ReturnAction(MouseEvent event) {
		try {
			int r_no = RentalView.getSelectionModel().getSelectedItem().getR_No();

			RentalDAO rDao = new RentalDAO();
			RentalVO rVo = new RentalVO();

			rVo = rDao.getRentalReturnsearch(r_no);

			if (rVo.getRental_Ok().equals("�� ��")) {
				rDao.getRentalReturn(r_no);

				ReData.removeAll(ReData);
				totalList2();
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("���뿩 �ȳ�");
				alert.setHeaderText("�̹� �뿩�� ��� �ݳ��Ǿ����ϴ�.");
				alert.showAndWait();
			}

		} catch (Exception e) {
			e.printStackTrace();

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���뿩 �ȳ�");
			alert.setHeaderText("���뿩���� ���濡 �����Ͽ����ϴ�.");
			alert.showAndWait();
		}

	}

	// �뿩�ǿ��� ��� ��ư �̺�Ʈ
	public void handlerBtnR_OkAction(MouseEvent event) {
		try {
			if (event.getSource().equals(btnR_Ok)) {

				RentalVO rVo = new RentalVO();
				RentalDAO rDao = new RentalDAO();
				CoronationDAO cDao = new CoronationDAO();
				CoronationVO cVo = new CoronationVO();
				cVo = cDao.getCoronationDateSearch(RentalView.getSelectionModel().getSelectedItem().getC_No1());

				int p = Integer.parseInt(txtFloor_P.getText());
				int p1 = Integer.parseInt(txtLodder_P.getText());
				int p2 = Integer.parseInt(txtCloth_P.getText());
				int p3 = Integer.parseInt(txtFog_P.getText());
				int p4 = Integer.parseInt(txtCanterbury_P.getText());

				int p5 = Integer.parseInt(txtLConsol_P.getText());
				int p6 = Integer.parseInt(txtPar_64_P.getText());
				int p7 = Integer.parseInt(txtElip_P.getText());
				int p8 = Integer.parseInt(txtMoving_P.getText());
				int p9 = Integer.parseInt(txtPar_32_P.getText());
				int p10 = Integer.parseInt(txtLaser_P.getText());

				int p11 = Integer.parseInt(txtMConsol_P.getText());
				int p12 = Integer.parseInt(txtCmic_P.getText());
				int p13 = Integer.parseInt(txtSpeaker_P.getText());
				int p14 = Integer.parseInt(txtWoofer_P.getText());
				int p15 = Integer.parseInt(txtWmic_P.getText());
				int p16 = Integer.parseInt(txtPwAmp_P.getText());

				rVo = new RentalVO(cVo.getStart_Date(), cVo.getEnd_Date(), p + p1 + p2 + p3 + p4,
						p5 + p6 + p7 + p8 + p9 + p10, p11 + p12 + p13 + p14 + p15 + p16,
						p + p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8 + p9 + p10 + p11 + p12 + p13 + p14 + p15 + p16,
						Integer.parseInt(txtR_Total.getText()));

				rVo.setP_Name(RentalView.getSelectionModel().getSelectedItem().getP_Name());

				rDao.getRentalUpdate(rVo);

				ReData.removeAll(ReData);
				totalList2();

				r_init();

				chFloor.setDisable(false);
				chLodder.setDisable(false);
				chCloth.setDisable(false);
				chFog.setDisable(false);
				chCanterbury.setDisable(false);
				chCool.setDisable(false);
				chHeat.setDisable(false);

				chLConsol.setDisable(false);
				chPar_64.setDisable(false);
				chElip.setDisable(false);
				chMoving.setDisable(false);
				chPar_32.setDisable(false);
				chLaser.setDisable(false);

				chMConsol.setDisable(false);
				chCmic.setDisable(false);
				chSpeaker.setDisable(false);
				chWoofer.setDisable(false);
				chWmic.setDisable(false);
				chPwAmp.setDisable(false);

				txtFloor_P.setEditable(true);
				txtLodder_P.setEditable(true);
				txtCloth_P.setEditable(true);
				txtFog_P.setEditable(true);
				txtCanterbury_P.setEditable(true);

				txtLConsol_P.setEditable(true);
				txtPar_64_P.setEditable(true);
				txtElip_P.setEditable(true);
				txtMoving_P.setEditable(true);
				txtPar_32_P.setEditable(true);
				txtLaser_P.setEditable(true);

				txtMConsol_P.setEditable(true);
				txtCmic_P.setEditable(true);
				txtSpeaker_P.setEditable(true);
				txtWoofer_P.setEditable(true);
				txtWmic_P.setEditable(true);
				txtPwAmp_P.setEditable(true);

				btnR_Total.setDisable(false);
				txtR_Total.setEditable(true);
				btnR_Ok.setDisable(false);
				btnR_Init.setDisable(false);

			}

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("��� �뿩 ��� ����");
			alert.setHeaderText("��� �뿩 ��� ������ �߻��Ͽ����ϴ�.");
			alert.setContentText("�ٽ� �Է��Ͻÿ�.");
			alert.showAndWait();
			e.printStackTrace();

		}

	}

	// �뿩�ǿ��� ��Ͻ� �ʱ�ȭ ��ư
	public void handlerBtnR_InitAction(ActionEvent event) {
		r_init();

	}

	public void r_init() {
		chFloor.setSelected(false);
		chLodder.setSelected(false);
		chCloth.setSelected(false);
		chFog.setSelected(false);
		chCanterbury.setSelected(false);
		chCool.setSelected(false);
		chHeat.setSelected(false);

		chLConsol.setSelected(false);
		chPar_64.setSelected(false);
		chElip.setSelected(false);
		chMoving.setSelected(false);
		chPar_32.setSelected(false);
		chLaser.setSelected(false);

		chMConsol.setSelected(false);
		chCmic.setSelected(false);
		chSpeaker.setSelected(false);
		chWoofer.setSelected(false);
		chWmic.setSelected(false);
		chPwAmp.setSelected(false);

		txtFloor_P.clear();
		txtLodder_P.clear();
		txtCloth_P.clear();
		txtFog_P.clear();
		txtCanterbury_P.clear();

		txtLConsol_P.clear();
		txtPar_64_P.clear();
		txtElip_P.clear();
		txtMoving_P.clear();
		txtPar_32_P.clear();
		txtLaser_P.clear();

		txtMConsol_P.clear();
		txtCmic_P.clear();
		txtSpeaker_P.clear();
		txtWoofer_P.clear();
		txtWmic_P.clear();
		txtPwAmp_P.clear();

	}

}
