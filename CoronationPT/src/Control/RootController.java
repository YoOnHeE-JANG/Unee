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
	private TableView<SuperlativesVO> tableView = new TableView<>(); // 대관자 등록 테이블
	@FXML
	private TableView<CoronationVO> CoTableView = new TableView<>(); // 대관 세부등록 테이블
	@FXML
	private TableView<RentalVO> RentalView = new TableView<>(); // 대관자의 장비대여테이블
	@FXML
	private TextField txtP_Name; // 대관자명
	@FXML
	private Button btnSearch; // 대관현황에서 대관자 검색 버튼
	@FXML
	private Button btnEdit; // 대관현황에서 정보수정 버튼
	@FXML
	private Button btnTotalList; // 상세정보버튼
	@FXML
	private Button btnDelete; // 대관현황에서 대관정보 모두 삭제하는 버튼
	@FXML
	private Button btnRental; // 장비대여->대여창을 띄워주는 버튼
	@FXML
	private Button btnMonthCost; // 월별매출통계 그래프 버튼
	@FXML
	private Button btnC_Pay; // 대관결제완료 버튼
	@FXML
	private Button btnR_Pay; // 대여결제완료 버튼
	@FXML
	private Button btnD_Pay; // 파손결제완료 버튼
	@FXML
	private TextField txtGroupName; // 단체명
	@FXML
	private TextField txtTitle; // 공연명
	@FXML
	private ComboBox<String> cbContact; // 연락처 콤보
	@FXML
	private TextField txtContactAll; // 연락처 전체
	@FXML
	private TextField txtContact1; // 연락처 앞자리
	@FXML
	private TextField txtContact2; // 연락처 뒷자리
	@FXML
	private TextField txtPrize; // 사업자등록번호
	@FXML
	private TextField txtAddress;// 주소

	@FXML
	private TextField txtC_Search; // 대관현황에서 검색란 - 대관자명검색
	@FXML
	private Button btnC_Search; // 대관현황에서 검색 - 검색 버튼
	@FXML
	private Button btnC_Totallist; // 대관현황전체보기 버튼
	@FXML
	private Button btnC_Delete; // 대관현황 선택 정보 삭제 버튼
	@FXML
	private ToggleGroup hallGroup; // 극장그룹
	@FXML
	private RadioButton rbMae; // 메극장
	@FXML
	private RadioButton rbSo; // 소극장
	@FXML
	private RadioButton rbDe; // 드극장
	@FXML
	private TextField txtHallName; // 공연장
	@FXML
	private Button btnOk; // 대관등록버튼
	@FXML
	private Button btnInit; // 대관초기화버튼
	@FXML
	private DatePicker dpStart; // 대관시작일
	@FXML
	private DatePicker dpEnd; // 대관종료일
	@FXML
	private CheckBox chMorning1; // 준비시간 -오전
	@FXML
	private CheckBox chAfternoon1; // 준비시간 -오후
	@FXML
	private CheckBox chNight1; // 준비시간 -철야
	@FXML
	private TextField txtMorning1; // 준비 -오전-수량
	@FXML
	private TextField txtAfternoon1; // 준비-오후-수량
	@FXML
	private TextField txtNight1; // 준비-철야-수량
	@FXML
	private CheckBox chMorning2; // 철거시간 - 오전
	@FXML
	private CheckBox chAfternoon2; // 철거시간 -오후
	@FXML
	private CheckBox chNight2; // 철거시간 -철야
	@FXML
	private TextField txtMorning2; // 철거시간 -오전-수량
	@FXML
	private TextField txtAfternoon2; // 철거시간-오후-수량
	@FXML
	private TextField txtNight2; // 철거시간-철야-수량
	@FXML
	private TextField txtD_day; // 공연일 수량
	@FXML
	private Button btnC_Total; // 대관총액정산 버튼
	@FXML
	private TextField txtC_Total; // 대관총액정산
	@FXML
	private Button btnR_Search; // 대여현황 대관자명 검색버튼
	@FXML
	private Button btnR_Edit; // 장비대여등록 버튼

	@FXML
	private Button btnR_Delete; // 대관자정보삭제 버튼
	@FXML
	private Button btnR_Return; // 반납시 대여중->반납으로 변경 버튼
	@FXML
	private Button btnR_Damage; // 파손청구 버튼
	@FXML
	private DatePicker dpR_Start; // 물품대여일
	@FXML
	private DatePicker dpR_End; // 물품반품일
	@FXML
	private Button btnR_CostInfo; // 대여비용정보
	@FXML
	private CheckBox chFloor; // 덧마루체크
	@FXML
	private CheckBox chFog; // 포그머신 체크
	@FXML
	private CheckBox chLodder; // 사다리 체크
	@FXML
	private CheckBox chCanterbury; // 보면대 체크
	@FXML
	private CheckBox chCloth; // 흑막천 체크
	@FXML
	private CheckBox chCool; // 냉방 체크
	@FXML
	private CheckBox chHeat; // 난방 체크
	@FXML
	private CheckBox chLConsol; // 조명기 콘솔 체크
	@FXML
	private CheckBox chMoving; // 무빙기 체크
	@FXML
	private CheckBox chPar_64; // 대파 체크
	@FXML
	private CheckBox chPar_32; // 소파 체크
	@FXML
	private CheckBox chElip; // 엘립소이달 체크
	@FXML
	private CheckBox chLaser; // 레이저 체크
	@FXML
	private CheckBox chMConsol; // 음향기 콘솔 체크
	@FXML
	private CheckBox chWoofer; // 우퍼 체크
	@FXML
	private CheckBox chCmic; // 유선마이크 체크
	@FXML
	private CheckBox chWmic; // 무선마이크체크
	@FXML
	private CheckBox chSpeaker; // 스피커 체크
	@FXML
	private CheckBox chPwAmp; // 파워앰프 체크
	@FXML
	private TextField txtFloor_P; // 덧마루 대여 수량
	@FXML
	private TextField txtFloor_N; // 덧마루 현재 수량
	@FXML
	private TextField txtLodder_P; // 사다리 대여 수량
	@FXML
	private TextField txtLodder_N; // 사다리 현재 수량
	@FXML
	private TextField txtCloth_P; // 흑막천 대여 수량
	@FXML
	private TextField txtCloth_N; // 흑막천 현재 수량
	@FXML
	private TextField txtFog_P; // 보면대 대여 수량
	@FXML
	private TextField txtFog_N; // 보면대 현재 수량
	@FXML
	private TextField txtCanterbury_P; // 보면대 대여 수량
	@FXML
	private TextField txtCanterbury_N; // 보면대 현재 수량
	@FXML
	private TextField txtLConsol_P; // 조명 콘솔 대여 수량
	@FXML
	private TextField txtLConsol_N; // 조명 콘솔 현재 수량
	@FXML
	private TextField txtMoving_P; // 무빙 대여 수량
	@FXML
	private TextField txtMoving_N; // 무빙 현재 수량
	@FXML
	private TextField txtPar_64_P; // 대파 대여 수량
	@FXML
	private TextField txtPar_64_N; // 대파 현재 수량
	@FXML
	private TextField txtPar_32_P; // 소파 대여 수량
	@FXML
	private TextField txtPar_32_N; // 소파 현재 수량
	@FXML
	private TextField txtElip_P; // 엘립 대여 수량
	@FXML
	private TextField txtElip_N; // 엘립 현재 수량
	@FXML
	private TextField txtLaser_P; // 레이저 대여 수량
	@FXML
	private TextField txtLaser_N; // 레이저 현재 수량
	@FXML
	private TextField txtMConsol_P; // 음향기 콘솔 대여 수량
	@FXML
	private TextField txtMConsol_N; // 음향기 콘솔 현재 수량
	@FXML
	private TextField txtWoofer_P; // 우퍼 대여 수량
	@FXML
	private TextField txtWoofer_N; // 우퍼 현재 수량
	@FXML
	private TextField txtCmic_P; // 유선마이크 대여 수량
	@FXML
	private TextField txtCmic_N; // 유선마이크 현재 수량
	@FXML
	private TextField txtWmic_P; // 무선마이크 대여 수량
	@FXML
	private TextField txtWmic_N; // 무선마이크 현재 수량
	@FXML
	private TextField txtSpeaker_P; // 스피커 대여수량
	@FXML
	private TextField txtSpeaker_N; // 스피커 현재수량
	@FXML
	private TextField txtPwAmp_P; // 파워 앰프 대여 수량
	@FXML
	private TextField txtPwAmp_N; // 파워 앰프 현재 수량
	@FXML
	private Button btnR_Total; // 대여 총액 정산 버튼
	@FXML
	private TextField txtR_Total; // 대여 총액 결과
	@FXML
	private Button btnR_Ok; // 대여 등록 버튼
	@FXML
	private Button btnR_Init; // 대여 초기화
	@FXML
	private Button btnR_Exit; // 창닫기 버튼
	@FXML
	private TextField txtSearch;// 검색필드
	@FXML
	private TextField txtR_Search; // 대여검색필드
	@FXML
	private Button btnR_TotalList; // 대여전체보기 버튼
	@FXML
	private Button btnPOk; // 대관자 등록 버튼
	@FXML
	private Button btnC_Info; // 대관정보입력 버튼
	@FXML
	private TextField txtPayMent; // 대관액 결제
	@FXML
	private TextField txtR_Name; // 대여에서 대관자명
	@FXML
	private Tab RentalTab; // 대여탭
	@FXML
	private TabPane tabpane; // 탭탭

	SuperlativesVO sVo = new SuperlativesVO();
	CoronationVO cVo = new CoronationVO();
	ObservableList<SuperlativesVO> data = FXCollections.observableArrayList();
	ObservableList<CoronationVO> CoData = FXCollections.observableArrayList();
	ObservableList<RentalVO> ReData = FXCollections.observableArrayList();
	ObservableList<SuperlativesVO> selectSuperlativesVO = null; // 테이블에서 선택한 정보 저장
	ObservableList<CoronationVO> selectCoronationVO = null;
	ObservableList<RentalVO> selectRentalVO = null;
	boolean editDelete = false; // 수정할 때 확인 버튼 상태 설정
	int selectedIndex; // 테이블에서 선택한 대관자 정보 인덱스 저장
	int no; // 삭제시 테이블에서 선택한 대관번호 저장
	String name; // 삭제시 테이블에서 선택한 이름 저장

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// 버튼 및 텍스트 필드초기상태 설정
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

		// 대관자등록 테이블뷰에 들어갈 컬럼 이름설정
		TableColumn colP_Name = new TableColumn("대관자명");
		colP_Name.setMinWidth(80);
		colP_Name.setStyle("-fx-alignment:CENTER");
		colP_Name.setCellValueFactory(new PropertyValueFactory<>("p_Name"));
		TableColumn colGroupName = new TableColumn("단체명");
		colGroupName.setMinWidth(120);
		colGroupName.setStyle("-fx-alignment:CENTER");
		colGroupName.setCellValueFactory(new PropertyValueFactory<>("groupName"));
		TableColumn colTitle = new TableColumn("공연명");
		colTitle.setMinWidth(150);
		colTitle.setStyle("-fx-alignment:CENTER");
		colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
		TableColumn colAddress = new TableColumn("주소");
		colAddress.setMinWidth(275);
		colAddress.setStyle("-fx-alignment:CENTER");
		colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
		TableColumn colContactAll = new TableColumn("연락처");
		colContactAll.setMinWidth(115);
		colContactAll.setStyle("-fx-alignment:CENTER");
		colContactAll.setCellValueFactory(new PropertyValueFactory<>("contactAll"));
		TableColumn colPrize = new TableColumn("사업등록번호");
		colPrize.setMinWidth(100);
		colPrize.setStyle("-fx-alignment:CENTER");

		colPrize.setCellValueFactory(new PropertyValueFactory<>("prize"));

		tableView.getColumns().addAll(colP_Name, colGroupName, colTitle, colAddress, colContactAll, colPrize);

		// 대관자 전체 정보
		totalList();
		tableView.setItems(data);

		// ------------------------------------------------------------------------------------------------------

		// 대관정보등록 테이블뷰에 들어갈 칼럼 이름설정

		TableColumn colC_No = new TableColumn("대관번호");
		colC_No.setMinWidth(40);
		colC_No.setStyle("-fx-alignment:CENTER");
		colC_No.setCellValueFactory(new PropertyValueFactory<>("c_No"));
		TableColumn colP_Name1 = new TableColumn("대관자명");
		colP_Name1.setMinWidth(80);
		colP_Name1.setStyle("-fx-alignment:CENTER");
		colP_Name1.setCellValueFactory(new PropertyValueFactory<>("p_Name"));
		TableColumn colHall = new TableColumn("공연장");
		colHall.setMinWidth(40);
		colHall.setStyle("-fx-alignment:CENTER");
		colHall.setCellValueFactory(new PropertyValueFactory<>("hall"));
		TableColumn colStart_Date = new TableColumn("대관시작일");
		colStart_Date.setMinWidth(90);
		colStart_Date.setStyle("-fx-alignment:CENTER");
		colStart_Date.setCellValueFactory(new PropertyValueFactory<>("start_Date"));
		TableColumn colEnd_Date = new TableColumn("대관종료일");
		colEnd_Date.setMinWidth(90);
		colEnd_Date.setStyle("-fx-alignment:CENTER");
		colEnd_Date.setCellValueFactory(new PropertyValueFactory<>("end_Date"));
		TableColumn colC_Total = new TableColumn("대관액");
		colC_Total.setMinWidth(120);
		colC_Total.setStyle("-fx-alignment:CENTER");
		colC_Total.setCellValueFactory(new PropertyValueFactory<>("c_Total"));
		TableColumn colC_PayMent = new TableColumn("대관액 결제");
		colC_PayMent.setMinWidth(80);
		colC_PayMent.setStyle("-fx-alignment:CENTER");
		colC_PayMent.setCellValueFactory(new PropertyValueFactory<>("c_PayMent"));

		CoTableView.getColumns().addAll(colC_No, colP_Name1, colHall, colStart_Date, colEnd_Date, colC_Total,
				colC_PayMent);

		// 대관자 전체 정보
		totalList1();
		CoTableView.setItems(CoData);

		// ----------------------------------------------------------------------------------------------------

		TableColumn colR_No = new TableColumn("대여번호");
		colR_No.setPrefWidth(60);
		colR_No.setStyle("-fx-alignment:center");
		colR_No.setCellValueFactory(new PropertyValueFactory<>("r_No"));
		TableColumn colC_No1 = new TableColumn("대관번호");
		colC_No1.setPrefWidth(60);
		colC_No1.setStyle("-fx-alignment:center");
		colC_No1.setCellValueFactory(new PropertyValueFactory<>("c_No1"));
		TableColumn colP_Name2 = new TableColumn("대관자명");
		colP_Name2.setMinWidth(70);
		colP_Name2.setStyle("-fx-alignment:center");
		colP_Name2.setCellValueFactory(new PropertyValueFactory<>("p_Name"));
		TableColumn colRental_StartDate = new TableColumn("대여일");
		colRental_StartDate.setMinWidth(80);
		colRental_StartDate.setStyle("-fx-alignment:center");
		colRental_StartDate.setCellValueFactory(new PropertyValueFactory<>("rental_StartDate"));
		TableColumn colRental_EndDate = new TableColumn("반납일");
		colRental_EndDate.setMinWidth(80);
		colRental_EndDate.setStyle("-fx-alignment:center");
		colRental_EndDate.setCellValueFactory(new PropertyValueFactory<>("rental_EndDate"));
		TableColumn colDevice_P = new TableColumn("장치수량");
		colDevice_P.setPrefWidth(60);
		colDevice_P.setStyle("-fx-alignment:center");
		colDevice_P.setCellValueFactory(new PropertyValueFactory<>("device_P"));
		TableColumn colLight_P = new TableColumn("조명기 수");
		colLight_P.setPrefWidth(60);
		colLight_P.setStyle("-fx-alignment:center");
		colLight_P.setCellValueFactory(new PropertyValueFactory<>("light_P"));
		TableColumn colSound_P = new TableColumn("음향기 수");
		colSound_P.setPrefWidth(60);
		colSound_P.setStyle("-fx-alignment:center");
		colSound_P.setCellValueFactory(new PropertyValueFactory<>("sound_P"));
		TableColumn colRental_Total = new TableColumn("총 수량");
		colRental_Total.setPrefWidth(60);
		colRental_Total.setStyle("-fx-alignment:center");
		colRental_Total.setCellValueFactory(new PropertyValueFactory<>("rental_Total"));
		TableColumn colRental_Pay = new TableColumn("총 대여액");
		colRental_Pay.setMinWidth(80);
		colRental_Pay.setStyle("-fx-alignment:center");
		colRental_Pay.setCellValueFactory(new PropertyValueFactory<>("rental_Pay"));
		TableColumn colRental_PayMent = new TableColumn("대여결제");
		colRental_PayMent.setMinWidth(50);
		colRental_PayMent.setStyle("-fx-alignment:center");
		colRental_PayMent.setCellValueFactory(new PropertyValueFactory<>("rental_PayMent"));
		TableColumn colRental_Ok = new TableColumn("대여상태");
		colRental_Ok.setMinWidth(50);
		colRental_Ok.setStyle("-fx-alignment:center");
		colRental_Ok.setCellValueFactory(new PropertyValueFactory<>("rental_Ok"));

		RentalView.getColumns().addAll(colR_No, colC_No1, colP_Name2, colRental_StartDate, colRental_EndDate,
				colDevice_P, colLight_P, colSound_P, colRental_Total, colRental_Pay, colRental_PayMent, colRental_Ok);

		totalList2();
		RentalView.setItems(ReData);

		// -----------------------------------------------------------------------------------------------------

		System.out.println("------1------");
		// 연락처 콤보박스에 들어갈 것
		cbContact.setItems(
				FXCollections.observableArrayList("010", "011", "016", "017", "018", "019", "02", "031", "032", "033",
						"041", "042", "043", "044", "051", "052", "053", "054", "055", "061", "062", "063", "064"));
		System.out.println("------2------");
		// 텍스트필드 숫자만 입력하도록 지정------------------------------------
		DecimalFormat format = new DecimalFormat("##########");
		// 대관정보 연락처, 사업자등록번호 필드에 숫자만 입력

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
		// 대관시간별 수량필드에 숫자만 입력
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

		// 전체보기 뽑기
		btnTotalList.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					data.removeAll(data);
					// 대관자 전체 정보
					totalList();
				} catch (Exception e) {

				}
			}
		});

		// 대관자 정보 등록
		btnPOk.setOnAction(event -> {
			try {
				data.removeAll(data);
				SuperlativesVO sVo = new SuperlativesVO();
				SuperlativesDAO sDao = new SuperlativesDAO();

				// 대관자 정보 저장
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
						alert.setTitle("대관자 정보 입력");
						alert.setHeaderText(txtP_Name.getText() + "님의 정보가 성공적으로 추가되었습니다.");

						alert.showAndWait();

					}

				}

			} catch (Exception ie) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("대관자 정보 입력");
				alert.setHeaderText("대관자 정보를 정확히 입력하시오.");
				alert.showAndWait();
			}
		});

		// 대관현황 전체 리스트보기
		btnC_Totallist.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					CoData.removeAll(CoData);

					// 대관현황 전체 정보
					totalList1();
				} catch (Exception e) {

				}

			}

		});
		// -------------------------------------------------------------------------------------------------------------
		// 대관 총액 구하기
		btnC_Total.setOnAction(event -> handlerBtnC_TotalAction(event));
		// 대관자이름으로 등록된 대관자정보 검색
		btnSearch.setOnAction(event -> handlerBtnSearchAction(event));
		// 대관자 테이블에 이벤트 처리
		tableView.setOnMousePressed(event -> handlertableViewAction(event));
		// 수정시 버튼
		btnEdit.setOnAction(event -> handlerBtnEditAction(event));
		// 대관세부정보입력버튼누르면 세부정보입력가능하게 필드들 열기
		btnC_Info.setOnAction(event -> handlerBtnC_InfoAction(event));
		// 대관자 정보 삭제 버튼
		btnDelete.setOnMouseClicked(event -> handlerBtnDeleteAction(event));
		// 대관자의 대관상세정보 등록 버튼
		btnOk.setOnAction(event -> handlerBtnOkAction(event));
		// 대관현황에서 대관자 검색 버튼
		btnC_Search.setOnAction(event -> handlerBtnC_SearchAction(event));
		// 대관정보입력 텍스트 필드 초기화 버튼
		btnInit.setOnAction(event -> handlerBtnInitAction(event));
		// <대관자의 대관결제 미납 -> 완납 > 변경버튼
		btnC_Pay.setOnMouseClicked(event -> handlerBtnC_PayAction(event));
		// <대관자의 대관결제 미납 -> 완납 > 테이블 클릭 이벤트 처리
		CoTableView.setOnMousePressed(event -> handlerCoTableViewAction(event));
		// 대관현황에서 대관자의 대관상세정보 삭제버튼
		btnC_Delete.setOnMouseClicked(event -> handlerBtnC_DeleteAction(event));
		// 첫번째 탭에서 장비대여를 눌렀을 경우에 대여 탭으로 넘어가면서
		btnRental.setOnMouseClicked(event -> handlerBtnRentalAction(event));
		// 대여탭에서 대여리스트 전체보기
		btnR_TotalList.setOnAction(event -> handlerBtnR_TotalListAction(event));
		// 대여탭에서 대관자=대여자 검색하기
		btnR_Search.setOnAction(event -> handlerBtnR_SearchAction(event));
		// 대여테이블 클릭이벤트
		RentalView.setOnMousePressed(event -> handlerRentalViewAction(event));
		// 장비대여 총액 정산 버튼이벤트
		btnR_Total.setOnAction(event -> handlerBtnR_TotalAction(event));
		// 장비대여등록버튼눌렀을때 대여장비항목입력가능 이벤트
		btnR_Edit.setOnAction(event -> handlerBtnR_EditAction(event));
		// 대여탭 눌렀을 때 대여장비항목 컨트롤 off
		RentalTab.setOnSelectionChanged(event -> handlerRentalTabAction(event));
		// 대여탭에서 리스트 삭제버튼 이벤트
		btnR_Delete.setOnMouseClicked(event -> handlerBtnR_DeleteAction(event));
		// 대여탭에서 장비결제 완료버튼 이벤트
		btnR_Pay.setOnMouseClicked(event -> handlerBtnR_PayAction(event));
		// 대여탭에서 장비반납 완료버튼 이벤트
		btnR_Return.setOnMouseClicked(event -> handlerBtnR_ReturnAction(event));
		// 대여탭에서 등록 버튼 이벤트
		btnR_Ok.setOnMouseClicked(event -> handlerBtnR_OkAction(event));
		// 대여탭에서 등록시 초기화 버튼
		btnR_Init.setOnAction(event -> handlerBtnR_InitAction(event));

		// -----------------------------------------------------------------------------------------------------------------
	}

	// 대관 총액 정산
	public void handlerBtnC_TotalAction(ActionEvent event) {

		// 라디오 버튼으로 공연장 선택과 선택한 공연장의 수량 필수 입력
		int basePrice = 0;
		if (rbMae.isSelected() && !txtD_day.getText().equals("")) {
			basePrice = Integer.parseInt(txtD_day.getText()) * 150000;
		} else if (rbSo.isSelected() && !txtD_day.getText().equals("")) {
			basePrice = Integer.parseInt(txtD_day.getText()) * 180000;
		} else if (rbDe.isSelected() && !txtD_day.getText().equals("")) {
			basePrice = Integer.parseInt(txtD_day.getText()) * 400000;
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("공연장 선택 오류");
			alert.setHeaderText("공연장과 공연일수는 꼭 입력하시오.");
			alert.showAndWait();
			return;
		}
		// 배열로 체크박스 체크시에 들어오는 값 조건연산식
		int[] partPrice = new int[6];
		for (int i = 0; i < partPrice.length; i++) {
			partPrice[i] = 0;
		}
		if (chMorning1.isSelected() && !txtMorning1.getText().equals("")) {
			partPrice[0] = Integer.parseInt(txtMorning1.getText()) * 30000;
		} else if (chMorning1.isSelected() && txtMorning1.getText().equals("")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("수량 입력 오류");
			alert.setHeaderText("공연장과 공연일수는 꼭 입력하시오.");
			alert.showAndWait();
			return;

		}

		if (chAfternoon1.isSelected() && !txtAfternoon1.getText().equals("")) {
			partPrice[1] = Integer.parseInt(txtAfternoon1.getText()) * 40000;
		} else if (chAfternoon1.isSelected() && txtAfternoon1.getText().equals("")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("수량 입력 오류");
			alert.setHeaderText("공연장과 공연일수는 꼭 입력하시오.");
			alert.showAndWait();
			return;

		}

		if (chNight1.isSelected() && !txtNight1.getText().equals("")) {
			partPrice[2] = Integer.parseInt(txtNight1.getText()) * 45000;
		} else if (chNight1.isSelected() && txtNight1.getText().equals("")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("수량 입력 오류");
			alert.setHeaderText("공연장과 공연일수는 꼭 입력하시오.");
			alert.showAndWait();
			return;

		}

		if (chMorning2.isSelected() && !txtMorning2.getText().equals("")) {
			partPrice[3] = Integer.parseInt(txtMorning2.getText()) * 30000;
		} else if (chMorning2.isSelected() && txtMorning2.getText().equals("")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("수량 입력 오류");
			alert.setHeaderText("공연장과 공연일수는 꼭 입력하시오.");
			alert.showAndWait();
			return;

		}
		if (chAfternoon2.isSelected() && !txtAfternoon2.getText().equals("")) {
			partPrice[4] = Integer.parseInt(txtAfternoon2.getText()) * 40000;
		} else if (chAfternoon2.isSelected() && txtAfternoon2.getText().equals("")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("수량 입력 오류");
			alert.setHeaderText("공연장과 공연일수는 꼭 입력하시오.");
			alert.showAndWait();
			return;

		}
		if (chNight2.isSelected() && !txtNight2.getText().equals("")) {
			partPrice[5] = Integer.parseInt(txtNight2.getText()) * 45000;
		} else if (chNight2.isSelected() && txtNight2.getText().equals("")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("수량 입력 오류");
			alert.setHeaderText("공연장과 공연일수는 꼭 입력하시오.");
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

	// 대관자이름으로 등록된 대관자정보 검색이벤트
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
				System.out.println("하");
				searchResult = true;
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("대관자 정보 검색");
				alert.setHeaderText("등록된 대관자의 이름을 입력하시오.");
				alert.showAndWait();

			}
			if (!searchP_Name.equals("") && (sVo != null)) {
				System.out.println("아");
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
				alert.setTitle("대관자 정보 검색");
				alert.setHeaderText(searchP_Name + "님은 등록이 되어있지 않습니다.");
				alert.setContentText("등록된 대관자로 다시 검색하시오.");
				alert.showAndWait();

			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("대관자 정보 검색 오류");
			alert.setHeaderText("대관자 정보 검색에 오류가 발생하였습니다.");
			alert.setContentText("다시 입력하시오.");
			alert.showAndWait();
		}
	}

	// 대관자 전체리스트
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

	// 대관자의 대관 전체리스트
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

	// 대관자의 대여 전체리스트
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

	// 테이블 클릭 이벤트
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
			alert.setTitle("대관자 처리");
			alert.setHeaderText("선택한 대관자로 수행할 작업을 선택하시오." + "");
			alert.showAndWait();
		} catch (Exception e) {

		}

	}

	// 배열을 화면에, 요소별로 알기 쉽게 출력
	public static void dumpArray(String[] array) {
		for (int i = 0; i < array.length; i++)
			System.out.format("array[%d] = %s\n", i, array[i]);
	}

	// 대관자 수정 이벤트 처리
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
			alert.setTitle("대관자 정보 수정 오류");
			alert.setHeaderText("대관자 정보 수정에 오류가 발생하였습니다.");
			alert.setContentText("다시 입력하시오.");
			alert.showAndWait();

		}

	}

	// 대관정보입력 버튼 설정이벤트
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

	// 대관자 정보 삭제 이벤트 처리
	public void handlerBtnDeleteAction(MouseEvent event) {
		// 대관자 삭제버튼
		SuperlativesDAO sDao = null;

		sDao = new SuperlativesDAO();

		try {
			String name = tableView.getSelectionModel().getSelectedItem().getP_Name();
			sDao.getSuperlativesDelete(name);
			data.removeAll(data);

			// 대관자 전체 정보
			totalList();

			// 버튼 설정
			btnEdit.setDisable(true);
			// 텍스트 필드 설정
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

	// 대관자의 대관정보 등록이벤트
	public void handlerBtnOkAction(ActionEvent event) {
		try {
			CoData.removeAll(CoData);
			CoronationVO cVo = null;
			CoronationDAO cDao = new CoronationDAO();

			if (event.getSource().equals(btnOk)) {
				cVo = new CoronationVO(txtP_Name.getText(), hallGroup.getSelectedToggle().getUserData().toString(),
						Date.valueOf(dpStart.getValue()), Date.valueOf(dpEnd.getValue()),
						Integer.parseInt(txtC_Total.getText()), "미  납");
				cDao = new CoronationDAO();
				cDao.getCoronationInfo(cVo);

				if (cDao != null) {
					totalList1();

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("대관정보 등록");
					alert.setHeaderText(txtP_Name.getText() + "님의 대관상세정보가 등록되었습니다.");

				}

			}
		} catch (Exception ie) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("대관정보 등록");
			alert.setHeaderText("대관정보를 정확히 입력하시오.");
			alert.showAndWait();
			ie.printStackTrace();
		}

	}

	// 대관현황에서 대관자 검색버튼 이벤트
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
				alert.setTitle("대관현황 검색");
				alert.setHeaderText("대관자명을 입력하시오.");
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
				alert.setTitle("대관현황 검색");
				alert.setHeaderText(searchP_Name + "님이 리스트에 없습니다.");
				alert.setContentText("다시 검색하세요.");
				alert.showAndWait();
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("대관현황 검색 오류");
			alert.setHeaderText("대관현황 검색에 오류가 발생하였습니다.");
			alert.setContentText("대관자명으로 다시 입력하시오.");
			alert.showAndWait();
		}
	}

	// 대관등록 모든 정보 텍스트필드 초기화 버튼 이벤트
	public void handlerBtnInitAction(ActionEvent event) {
		init();
	}

	// (대관등록 모든 정보 초기화 버튼)-> 초기화 메소드
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

	// 대관결제 미납에서 완납으로 변경하는 버튼
	public void handlerBtnC_PayAction(MouseEvent event) {
		try {
			int c_Num = CoTableView.getSelectionModel().getSelectedItem().getC_No();

			CoronationDAO cDao = new CoronationDAO();
			CoronationVO cVo = new CoronationVO();

			cVo = cDao.getCoronationPaysearch(c_Num);

			if (cVo.getC_PayMent().equals("미  납")) {
				cDao.getCoronationPay(c_Num);

				CoData.removeAll(CoData);
				totalList1();
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("대관총액 결제 안내");
				alert.setHeaderText("이미 완납되었습니다.");
				alert.showAndWait();
			}

		} catch (Exception e) {
			e.printStackTrace();

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("대관총액 결제 안내");
			alert.setHeaderText("대관총액 완납 변경에 실패하였습니다.");
			alert.showAndWait();
		}

	}

	// 대관테이블 클릭 이벤트 처리
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

	// 대관현황에서 대관자의 대관상세정보 삭제버튼
	public void handlerBtnC_DeleteAction(MouseEvent event) {

		CoronationDAO cDao = null;
		cDao = new CoronationDAO();

		try {
			if (event.getClickCount() != 2) {
				int c_num = CoTableView.getSelectionModel().getSelectedItem().getC_No();
				System.out.println(c_num);
				cDao.getCoronationDelete(c_num);
				CoData.removeAll(CoData);
				// 대관자 전체 정보
				totalList1();
				// 버튼 설정
				btnEdit.setDisable(true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// editDelete = true;

	}

	// 장비대여신청버튼이벤트
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
					Integer.parseInt(txtR_Total.getText()), "미 납", "대 여");
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
				alert.setTitle("대여정보 신청");
				alert.setHeaderText(txtP_Name.getText() + " 장비대여 신청이 완료되었습니다.");
				alert.setContentText("대여 탭을 눌러 대여할 장비를 선택하시오.");

				// tabpane.getSelectionModel().selectedItemProperty().
			}
		}

	}

	// 대여탭에서 대여리스트 전체보기
	public void handlerBtnR_TotalListAction(ActionEvent event) {

		try {
			ReData.removeAll(ReData);

			// 대관현황 전체 정보
			totalList2();
		} catch (Exception e) {

		}
	}

	// 대여탭에서 대관자=대여자 검색버튼 이벤트
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
				alert.setTitle("대여현황 검색");

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
				alert.setTitle("대여현황 검색");
				alert.setHeaderText(searchP_Name + "님이 리스트에 없습니다.");
				alert.setContentText("다시 검색하세요.");
				alert.showAndWait();

			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("대여현황 검색 오류");
			alert.setHeaderText("대여현황 검색에 오류가 발생하였습니다.");
			alert.setContentText("대관자(대여자)명으로 다시 입력하시오.");
			alert.showAndWait();

		}

	}

	// 대관테이블뷰 클릭이벤트
	public void handlerRentalViewAction(MouseEvent event) {
		selectRentalVO = RentalView.getSelectionModel().getSelectedItems();
		//selectedIndex = RentalView.getSelectionModel().getSelectedIndex();
		
		txtR_Name.setText(selectRentalVO.get(0).getP_Name());
		dpR_Start.setValue(selectRentalVO.get(0).getRental_StartDate().toLocalDate());
		dpR_End.setValue(selectRentalVO.get(0).getRental_EndDate().toLocalDate());

	}

	// 대여 총액 정산
	public void handlerBtnR_TotalAction(ActionEvent event) {

		int[] price = new int[18];

		for (int i = 0; i < price.length; i++) {
			price[i] = 0;
		}
		// 장치 및 시설 연산식
		if (chFloor.isSelected() && !txtFloor_P.getText().equals("0")) {
			price[0] = Integer.parseInt(txtFloor_P.getText()) * 30000;
		} else if (chFloor.isSelected() && txtFloor_P.getText().equals("0")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("수량 입력 오류");
			alert.setHeaderText("선택한 항목의 수량을 꼭 입력하시오.");
			alert.showAndWait();

			return;
		}

		if (chLodder.isSelected() && !txtLodder_P.getText().equals("0")) {
			price[1] = Integer.parseInt(txtLodder_P.getText()) * 10000;
		} else if (chLodder.isSelected() && txtLodder_P.getText().equals("0")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("수량 입력 오류");
			alert.setHeaderText("선택한 항목의 수량을 꼭 입력하시오.");
			alert.showAndWait();

			return;
		}

		if (chCloth.isSelected() && !txtCloth_P.getText().equals("0")) {
			price[2] = Integer.parseInt(txtCloth_P.getText()) * 15000;
		} else if (chCloth.isSelected() && txtCloth_P.getText().equals("0")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("수량 입력 오류");
			alert.setHeaderText("선택한 항목의 수량을 꼭 입력하시오.");
			alert.showAndWait();

			return;
		}

		if (chFog.isSelected() && !txtFog_P.getText().equals("0")) {
			price[3] = Integer.parseInt(txtFog_P.getText()) * 20000;
		} else if (chFog.isSelected() && txtFog_P.getText().equals("0")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("수량 입력 오류");
			alert.setHeaderText("선택한 항목의 수량을 꼭 입력하시오.");
			alert.showAndWait();

			return;
		}

		if (chCanterbury.isSelected() && !txtCanterbury_P.getText().equals("0")) {
			price[4] = Integer.parseInt(txtCanterbury_P.getText()) * 1000;
		} else if (chCanterbury.isSelected() && txtCanterbury_P.getText().equals("0")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("수량 입력 오류");
			alert.setHeaderText("선택한 항목의 수량을 꼭 입력하시오.");
			alert.showAndWait();

			return;
		}

		if (chCool.isSelected()) {
			price[5] = 50000;
		} else if (chCool.isSelected()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("수량 입력 오류");
			alert.setHeaderText("선택한 항목의 수량을 꼭 입력하시오.");
			alert.showAndWait();

			return;
		}

		if (chHeat.isSelected()) {
			price[6] = 30000;
		} else if (chHeat.isSelected()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("수량 입력 오류");
			alert.setHeaderText("선택한 항목의 수량을 꼭 입력하시오.");
			alert.showAndWait();

			return;
		}

		// 조명기 연산식
		if (chLConsol.isSelected() && !txtLConsol_P.getText().equals("0")) {
			price[7] = Integer.parseInt(txtLConsol_P.getText()) * 40000;
		} else if (chLConsol.isSelected() && txtLConsol_P.getText().equals("0")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("수량 입력 오류");
			alert.setHeaderText("선택한 항목의 수량을 꼭 입력하시오.");
			alert.showAndWait();

			return;
		}

		if (chPar_64.isSelected() && !txtPar_64_P.getText().equals("0")) {
			price[8] = Integer.parseInt(txtPar_64_P.getText()) * 3000;
		} else if (chPar_64.isSelected() && txtPar_64_P.getText().equals("0")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("수량 입력 오류");
			alert.setHeaderText("선택한 항목의 수량을 꼭 입력하시오.");
			alert.showAndWait();

			return;
		}

		if (chElip.isSelected() && !txtElip_P.getText().equals("0")) {
			price[9] = Integer.parseInt(txtElip_P.getText()) * 5000;
		} else if (chElip.isSelected() && txtElip_P.getText().equals("0")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("수량 입력 오류");
			alert.setHeaderText("선택한 항목의 수량을 꼭 입력하시오.");
			alert.showAndWait();

			return;
		}

		if (chMoving.isSelected() && !txtMoving_P.getText().equals("0")) {
			price[10] = Integer.parseInt(txtMoving_P.getText()) * 20000;
		} else if (chMoving.isSelected() && txtMoving_P.getText().equals("0")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("수량 입력 오류");
			alert.setHeaderText("선택한 항목의 수량을 꼭 입력하시오.");
			alert.showAndWait();

			return;
		}

		if (chPar_32.isSelected() && !txtPar_32_P.getText().equals("0")) {
			price[11] = Integer.parseInt(txtPar_32_P.getText()) * 5500;
		} else if (chPar_32.isSelected() && txtPar_32_P.getText().equals("0")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("수량 입력 오류");
			alert.setHeaderText("선택한 항목의 수량을 꼭 입력하시오.");
			alert.showAndWait();

			return;
		}

		if (chLaser.isSelected() && !txtLaser_P.getText().equals("0")) {
			price[12] = Integer.parseInt(txtLaser_P.getText()) * 150000;
		} else if (chLaser.isSelected() && txtLaser_P.getText().equals("0")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("수량 입력 오류");
			alert.setHeaderText("선택한 항목의 수량을 꼭 입력하시오.");
			alert.showAndWait();

			return;
		}

		// 음향기 연산식
		if (chMConsol.isSelected() && !txtMConsol_P.getText().equals("")) {
			price[13] = Integer.parseInt(txtMConsol_P.getText()) * 40000;
		} else if (chMConsol.isSelected() && txtMConsol_P.getText().equals("0")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("수량 입력 오류");
			alert.setHeaderText("선택한 항목의 수량을 꼭 입력하시오.");
			alert.showAndWait();

			return;
		}

		if (chCmic.isSelected() && !txtCmic_P.getText().equals("0")) {
			price[14] = Integer.parseInt(txtCmic_P.getText()) * 5000;
		} else if (chCmic.isSelected() && txtCmic_P.getText().equals("0")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("수량 입력 오류");
			alert.setHeaderText("선택한 항목의 수량을 꼭 입력하시오.");
			alert.showAndWait();

			return;
		}

		if (chSpeaker.isSelected() && !txtSpeaker_P.getText().equals("0")) {
			price[15] = Integer.parseInt(txtSpeaker_P.getText()) * 23000;
		} else if (chSpeaker.isSelected() && txtSpeaker_P.getText().equals("0")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("수량 입력 오류");
			alert.setHeaderText("선택한 항목의 수량을 꼭 입력하시오.");
			alert.showAndWait();

			return;
		}

		if (chWoofer.isSelected() && !txtWoofer_P.getText().equals("0")) {
			price[16] = Integer.parseInt(txtWoofer_P.getText()) * 20000;
		} else if (chWoofer.isSelected() && txtWoofer_P.getText().equals("0")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("수량 입력 오류");
			alert.setHeaderText("선택한 항목의 수량을 꼭 입력하시오.");
			alert.showAndWait();

			return;
		}

		if (chWmic.isSelected() && !txtWmic_P.getText().equals("0")) {
			price[17] = Integer.parseInt(txtWmic_P.getText()) * 10000;
		} else if (chWmic.isSelected() && txtWmic_P.getText().equals("0")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("수량 입력 오류");
			alert.setHeaderText("선택한 항목의 수량을 꼭 입력하시오.");
			alert.showAndWait();

			return;
		}

		if (chPwAmp.isSelected() && !txtPwAmp_P.getText().equals("0")) {
			price[18] = Integer.parseInt(txtPwAmp_P.getText()) * 20000;
		} else if (chPwAmp.isSelected() && txtPwAmp_P.getText().equals("0")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("수량 입력 오류");
			alert.setHeaderText("선택한 항목의 수량을 꼭 입력하시오.");
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

	// 장비대여등록버튼눌렀을때 대여장비항목입력가능 이벤트
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

	// 대여탭 눌렀을 때 대여장비항목 컨트롤 off
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

	// 대여탭에서 리스트 삭제버튼 이벤트
	public void handlerBtnR_DeleteAction(MouseEvent event) {
		RentalDAO rDao = null;
		rDao = new RentalDAO();

		try {
			if (event.getClickCount() != 2) {
				int rno = RentalView.getSelectionModel().getSelectedItem().getR_No();
				System.out.println(rno);
				rDao.getRentalDelete(rno);
				ReData.removeAll(ReData);
				// 대관자 전체 정보
				totalList2();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 대여탭에서 장비결제 완료버튼 이벤트
	public void handlerBtnR_PayAction(MouseEvent event) {
		try {
			int r_Num = RentalView.getSelectionModel().getSelectedItem().getR_No();

			RentalDAO rDao = new RentalDAO();
			RentalVO rVo = new RentalVO();

			rVo = rDao.getRentalPaysearch(r_Num);

			if (rVo.getRental_PayMent().equals("미 납")) {
				rDao.getRentalPay(r_Num);

				ReData.removeAll(ReData);
				totalList2();
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("장비대여총액 결제 안내");
				alert.setHeaderText("이미 완납되었습니다.");
				alert.showAndWait();
			}

		} catch (Exception e) {
			e.printStackTrace();

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("장비대여 결제 안내");
			alert.setHeaderText("장비대여총액 완납 변경에 실패하였습니다.");
			alert.showAndWait();
		}
	}

	// 대여탭에서 장비반납 완료버튼 이벤트
	public void handlerBtnR_ReturnAction(MouseEvent event) {
		try {
			int r_no = RentalView.getSelectionModel().getSelectedItem().getR_No();

			RentalDAO rDao = new RentalDAO();
			RentalVO rVo = new RentalVO();

			rVo = rDao.getRentalReturnsearch(r_no);

			if (rVo.getRental_Ok().equals("대 여")) {
				rDao.getRentalReturn(r_no);

				ReData.removeAll(ReData);
				totalList2();
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("장비대여 안내");
				alert.setHeaderText("이미 대여한 장비가 반납되었습니다.");
				alert.showAndWait();
			}

		} catch (Exception e) {
			e.printStackTrace();

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("장비대여 안내");
			alert.setHeaderText("장비대여상태 변경에 실패하였습니다.");
			alert.showAndWait();
		}

	}

	// 대여탭에서 등록 버튼 이벤트
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
			alert.setTitle("장비 대여 등록 오류");
			alert.setHeaderText("장비 대여 등록 오류가 발생하였습니다.");
			alert.setContentText("다시 입력하시오.");
			alert.showAndWait();
			e.printStackTrace();

		}

	}

	// 대여탭에서 등록시 초기화 버튼
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
