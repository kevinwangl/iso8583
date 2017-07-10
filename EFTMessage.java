
/**
 * $Log: EFTMessage.java,v $
 * Revision 1.10  2008/02/27 02:00:33  liaok
 * 增加商户名称域
 *
 * Revision 1.9  2008/01/16 07:09:48  liaok
 * 更新EFTmessage的注释，为文档做准备
 *
 * Revision 1.8  2007/12/11 06:28:16  wangl
 * 修改uniquid为整型
 *
 * Revision 1.7  2007/12/06 07:29:34  wangl
 * 加入交易类型判断
 *
 * Revision 1.6  2007/11/28 07:04:57  liaok
 * 修正网络管理码的赋值问题
 *
 * Revision 1.5  2007/11/22 06:05:21  liaok
 * 增加TPDU字段并实现对其的赋值
 *
 * Revision 1.4  2007/11/20 10:54:11  liaok
 * 修改交易类型的getter为int型
 *
 * Revision 1.3  2007/11/16 06:31:31  liaok
 * 将部分get/set的内容从字符串变为int，方面逻辑使用
 *
 * Revision 1.2  2007/11/13 06:45:29  wangl
 * 同步代码
 *
 * Revision 1.4  2007/11/09 07:22:49  wangl
 * 加入域注释
 *
 * Revision 1.3  2007/11/01 02:33:53  wangl
 * 修改字段属性
 *
 * Revision 1.2  2007/10/26 07:39:44  wangl
 * 修改为保护类型
 *
 * Revision 1.1.1.1  2007/10/09 02:17:40  ndscm
 * 重新提交TPLATFORM的内容，将*.h、*.java、*.c、*.cpp以文本形式进行提交
 *
 * Revision 1.10  2007/09/29 07:28:51  wangl
 * 修改錯誤代碼
 *
 * Revision 1.9  2007/09/28 03:27:01  wangl
 * 添加结算部分
 *
 * Revision 1.8  2007/09/25 06:51:36  wangl
 * 添加交易部分代码
 *
 * Revision 1.7  2007/09/24 07:06:58  zhaol
 * 更改框架注册和处理方式,简化开发过程
 *
 * Revision 1.6  2007/09/20 03:23:11  wangl
 * 修改错误
 *
 *
 */




/**
 *	@brief
 *   <p><b>金融消息接口 符合ISO8583定义的数据包</b></p>
 * 
 *  &nbsp;&nbsp;&nbsp;&nbsp;金融消息接口 符合ISO8583定义的数据包
 * 
 *  @author wangl
 *  @version eJPos SDK1.0
 *  @see 
 *  @since 2007-9-5
 */


public class EFTMessage {
	
	/**
	 * 交易标识
	 */
	private int uniqueID;
	
	/**
	 * @brief
	 * 消息类型
	 * 
	 */
	public String msgTypeID;
	

	/**
	 * 交易类型 FIELD_60_1
	 */
	public String transactionType;
	
	/**
	 * 批次号 FIELD_60_2
	 */
	public String batchNumber;
	
	/**
	 * 应答码 FIELD_39
	 */
	public String responseCode = "00";
	
	/**
	 * 本地时间 12域
	 */
	public String localTransTime;
	
	/**
	 * 本地日期 13域
	 */
	public String localTransDate;
	
	/**
	 * 受卡方标识码 42域
	 */
	public String cardAcptIdenCode;
	
	/**
	 * 受卡方名称地址 43域
	 */
	public String cardAcptAddress;
	
	/**
	 * 受卡机终端标识码 FIELD_41
	 */
	public String serviceCode;
	
	/**
	 * 受卡方系统跟踪号 11域
	 */
	public String traceNumber;
	
	/**
	 * 主账号 2域
	 */
	public String priAccountNumber;
	
	/**
	 * 交易处理码 3域
	 */
	public String process;
	
	/**
	 * 交易金额 4域
	 */
	public String transactionAmount;
	
	/**
	 * 卡有效期 14域
	 */
	public String cardValidate;
	
	/**
	 * 服务点输入方式码 22域
	 */
	public String pointServiceInputMode;
	
	/**
	 * 服务点条件码 25域
	 */
	public String pointServiceCode;
	
	/**
	 * 服务点PIN获取码 26域
	 */
	public String pointServicePINCode;
	
	/**
	 * 2磁道数据 35域
	 */
	public String track2Data;
	
	/**
	 * 3磁道数据 36域
	 */
	public String track3Data;
	
	/**
	 * 交易货币代码 49域
	 */
	public String currencyTransactionCode;
	
	/**
	 * 个人标识码数据 52域
	 */
	public byte[] pinData;
	
	/**
	 * 安全控制信息 53域
	 */
	public String securityControlInfo;
	
	/**
	 * <code>mac</code> 64域
	 */
	public byte[] mac;
	
	/**
	 * 原批号FIELD_61_1
	 */
	public String originalBatchNumber;
	
	/**
	 * 原流水号FIELD_61_2
	 */
	public String originalTraceNumber;
	
	/**
	 * 原交易日期FIELD_61_3
	 */
	public String originalTransactionDate;
	
	/**
	 * 受理方标识码 32域
	 */
	public String acceptorSign;
	
	/**
	 * 检索参考号 37域
	 */
	public String retrievalRefNum;
	
	/**
	 * 终端密钥 62域
	 */
	public byte[] clientKey;
	
	/**
	 * 参数信息 2域
	 */
	public byte[] parameterInfo; 

	/**
	 * 清算日期 15域
	 */
	public String settlementdate;
	
	/**
	 * 授权标识应答码 38域
	 */
	public String authorizationIdentifyResCode;
	
	/**
	 * 附加响应数据 44域
	 */
	public String extInfo;
	
	/**
	 * 发卡行(定长8个字节) 63.2域
	 */
	public String publishBank;

	/**
	 * 收单行号(定长8个字节) 63.4域
	 */
	public String receiveBank;
	

	/**
	 * 国际组织代码(定长3个字节) 国际组织中文 国际组织英文 3位代码 人民币卡 China Union Pay CUP 威士卡 VISA VIS
	 * 万事达卡 Master Card MCC 万事达卡 Maestro Card MAE JCB卡 JCB JCB 大来卡 Dinner Club
	 * DCC 运通卡 American Express AEX 63.1域
	 *  
	 */
	public String internationalOrganizationCode;
	
	/**
	 * 银联信息 63.3域
	 */
	public String chinaUnionPayInfo;
	

	/**
	 * 操作员代码 63.1域
	 */
	public String operatorCode;
	
	
	/**
	 * 网络管理码 60.3域
	 */
	public String netManageCode;
	
	
	/**
	 * 附加金额 54
	 */
	public String balanceAmount;

	
	/**
	 * 60.3域 内卡借记总金额
	 */
	public String  localCardLoanTotalAmount; //48
	/**
	 * 60.3域 内卡借记总次数
	 */
	public String  localCardLoanTotalCount;//48
	/**
	 * 60.3域 内卡贷记总金额
	 */
	public String  localCardCreditTotalAmount;//48
	/**
	 * 60.3域 内卡贷记总次数
	 */
	public String  localCardCreditTotalCount;//48
	/**
	 * 60.3域 内卡状态
	 */
	public String  localCardCheckAccountCode;//48
	/**
	 * 60.3域 外卡借记总金额
	 */
	public String  foreignCardLoanTotalAmount;//48
	/**
	 * 60.3域 外卡借记总次数
	 */
	public String  foreignCardLoanTotalCount;//48
	/**
	 * 60.3域 外卡贷记总金额
	 */
	public String  foreignCardCreditTotalAmount;//48
	/**
	 * 60.3域 外卡贷记总次数
	 */
	public String  foreignCardCreditTotalCount;//48
	/**
	 * 60.3域 外卡状态
	 */
	public String  foreignCardCheckAccountCode;//48
	
	/**
	 * 原授权方式 61.4
	 */
	public String authorizationType;
	
	/**
	 * 原授权机构 61.5
	 */
	public String authorizationOrganization;
	
	/**
	 * 交易明细 48
	 */
	public String  transactionDetial;
	
	/**
	 * 数据包TPDU信息
	 */
	public String TPDU;
	
	/**
	 * <code>商户名称</code>
	 */
	public String merchantName;
	
	public byte[] serviceScript;
	
	public String serviceResponse;
	
	public byte[] serviceRequestCode;
	
	public byte[] serviceRequestPara;
	
	public byte[] serviceRequestSign;
	
	public String reserved;
	
	//-----------------------------------------------------------------------------------------------------------------------------------------//
	//QCUP银联多渠道平台数据域
	/**
	 * 域2  主账号
	 */
	public String qcup_PrimaryAccountNumber;
	
	/**
	 * 域3  交易处理码
	 */
	public String qcup_ProcessingCode;
	
	/**
	 * 域4  交易金额
	 */
	public String qcup_TransactionsAmount;
	
	/**
	 * 域7  交易传输时间
	 */
	public String qcup_TransmissionDateAndTime;
	
	/**
	 * 域11  系统跟踪号
	 */
	public String qcup_SystemTraceAuditNumber;
	
	/**
	 * 域12  受卡方所在地时间
	 */
	public String qcup_LocalTransactionTime;
	
	/**
	 * 域13  受卡方所在地日期
	 */
	public String qcup_LocalTransactionDate;
	
	/**
	 * 域14  卡有效期
	 */
	public String qcup_ExpirationDate;
	
	/**
	 * 域15  清算日期
	 */
	public String qcup_SettlementDate;
	
	/**
	 * 域18  商户类型
	 */
	public String qcup_MerchantType;
	
	/**
	 * 域22  服务点输入方式码
	 */
	public String qcup_PointOfServiceEntryModeCode;
	
	/**
	 * 域23  卡序列号
	 */
	public String qcup_CardSequenceNumber;
	
	/**
	 * 域25  服务点条件码
	 */
	public String qcup_PointOfServiceConditionMode;
	
	/**
	 * 域26服务点PIN 获取码
	 */
	public String qcup_PointofServicePinCaptureCode;
	
	/**
	 * 域32 受理机构标识码
	 */
	public String qcup_AcquiringInstitutionIdentificationCode;
	
	/**
	 * 域33 发送机构标识码
	 */
	public String qcup_ForwardingInstitutionIdentificationCode;
	
	/**
	 * 域35第二磁道数据
	 */
	public String qcup_Track2Data;
	
	/**
	 * 域36第三磁道数据
	 */
	public String qcup_Track3Data;
	
	/**
	 * 域37  检索参考号
	 */
	public String qcup_RetrievalReferenceNumber;
	
	/**
	 * 域38  授权标识应答码
	 */
	public String qcup_AuthorizationIdentificationResponse;
	
	/**
	 * 域39  应答码
	 */
	public String qcup_ResponseCode;
	
	/**
	 * 域41  受卡机终端标识码
	 */
	public String qcup_CardAcceptorTerminalIdentification;
	
	/**
	 * 域42  受卡方标识码
	 */
	public String qcup_CardAcceptorIdentificationCode;
	
	/**
	 * 域43 受卡方名称地址
	 */
	public String qcup_CardAcceptorNameLocation;
	
	/**
	 * 域48 自定义域
	 */
	public String qcup_AdditionalDataPrivate;
	
	/**
	 * 域49交易货币代码
	 */
	public String qcup_CurrencyCodeTransaction;
	
	/**
	 * 域52 个人标识码数据
	 */
	public byte[] qcup_pinData;
	
	/**
	 * 域53 安全控制信息
	 */
	public String qcup_SecurityRelatedControlInformation;
	
	/**
	 * 域54 实际余额
	 */
	public String qcup_AdditionalAmounts;
	
	/**
	 * 域55基于PBOC借贷记标准的IC卡数据域
	 */
	public byte[] qcup_IntegratedCircuitCardSystemRelatedData;
	
	/**
	 * 域59 明细查询数据
	 */
	public String qcup_DetailInquiring;
	
	/**
	 * 域60  自定义域
	 */
	public String qcup_AdditionalPointOfServiceInformation;
	
	/**
	 * 域61  持卡人身份认证信息
	 */
	public String qcup_AuthenticationInformation;
	
	/**
	 * 域63  自定义域
	 */
	public byte[] qcup_ReservedPrivate;
	
	/**
	 * 域70  网络管理码
	 */
	public String qcup_Networkmanagementcode;
	
	/**
	 * 域90  原始数据元
	 */
	public String qcup_OriginalDataElements;
	
	/**
	 * 域96  报文安全码
	 */
	public byte[] qcup_MessageSecurityCode;
	
	/**
	 * 域100  接收机构标识码
	 */
	public String qcup_DestinationInstituitionIdentificationCode;
	
	/**
	 * 域102  账户标识1
	 */
	public String qcup_AccountIdentification1;
	
	/**
	 * 域103  账户标识2
	 */
	public String qcup_AccountIdentification2;
	
	/**
	 * 域128  报文鉴别码
	 */
	public byte[] qcup_Mac;
	

	public String getQCUP_PrimaryAccountNumber() {
		return qcup_PrimaryAccountNumber;
	}

	public void setQCUP_PrimaryAccountNumber(String qCUP_PrimaryAccountNumber) {
		qcup_PrimaryAccountNumber = qCUP_PrimaryAccountNumber;
	}

	public String getQCUP_ProcessingCode() {
		return qcup_ProcessingCode;
	}

	public void setQCUP_ProcessingCode(String qCUP_ProcessingCode) {
		qcup_ProcessingCode = qCUP_ProcessingCode;
	}

	public String getQCUP_TransactionsAmount() {
		return qcup_TransactionsAmount;
	}

	public void setQCUP_TransactionsAmount(String qCUP_TransactionsAmount) {
		qcup_TransactionsAmount = qCUP_TransactionsAmount;
	}

	public String getQCUP_TransmissionDateAndTime() {
		return qcup_TransmissionDateAndTime;
	}

	public void setQCUP_TransmissionDateAndTime(String qCUP_TransmissionDateAndTime) {
		qcup_TransmissionDateAndTime = qCUP_TransmissionDateAndTime;
	}

	public String getQCUP_SystemTraceAuditNumber() {
		return qcup_SystemTraceAuditNumber;
	}

	public void setQCUP_SystemTraceAuditNumber(String qCUP_SystemTraceAuditNumber) {
		qcup_SystemTraceAuditNumber = qCUP_SystemTraceAuditNumber;
	}

	public String getQCUP_LocalTransactionTime() {
		return qcup_LocalTransactionTime;
	}

	public void setQCUP_LocalTransactionTime(String qCUP_LocalTransactionTime) {
		qcup_LocalTransactionTime = qCUP_LocalTransactionTime;
	}

	public String getQCUP_LocalTransactionDate() {
		return qcup_LocalTransactionDate;
	}

	public void setQCUP_LocalTransactionDate(String qCUP_LocalTransactionDate) {
		qcup_LocalTransactionDate = qCUP_LocalTransactionDate;
	}

	public String getQCUP_ExpirationDate() {
		return qcup_ExpirationDate;
	}

	public void setQCUP_ExpirationDate(String qCUP_ExpirationDate) {
		qcup_ExpirationDate = qCUP_ExpirationDate;
	}

	public String getQCUP_SettlementDate() {
		return qcup_SettlementDate;
	}

	public void setQCUP_SettlementDate(String qCUP_SettlementDate) {
		qcup_SettlementDate = qCUP_SettlementDate;
	}

	public String getQCUP_MerchantType() {
		return qcup_MerchantType;
	}

	public void setQCUP_MerchantType(String qCUP_MerchantType) {
		qcup_MerchantType = qCUP_MerchantType;
	}

	public String getQCUP_PointOfServiceEntryModeCode() {
		return qcup_PointOfServiceEntryModeCode;
	}

	public void setQCUP_PointOfServiceEntryModeCode(
			String qCUP_PointOfServiceEntryModeCode) {
		qcup_PointOfServiceEntryModeCode = qCUP_PointOfServiceEntryModeCode;
	}

	public String getQCUP_CardSequenceNumber() {
		return qcup_CardSequenceNumber;
	}

	public void setQCUP_CardSequenceNumber(String qCUP_CardSequenceNumber) {
		qcup_CardSequenceNumber = qCUP_CardSequenceNumber;
	}

	public String getQCUP_PointOfServiceConditionMode() {
		return qcup_PointOfServiceConditionMode;
	}

	public void setQCUP_PointOfServiceConditionMode(
			String qCUP_PointOfServiceConditionMode) {
		qcup_PointOfServiceConditionMode = qCUP_PointOfServiceConditionMode;
	}

	public String getQCUP_PointofServicePinCaptureCode() {
		return qcup_PointofServicePinCaptureCode;
	}

	public void setQCUP_PointofServicePinCaptureCode(
			String qCUP_PointofServicePinCaptureCode) {
		qcup_PointofServicePinCaptureCode = qCUP_PointofServicePinCaptureCode;
	}

	public String getQCUP_AcquiringInstitutionIdentificationCode() {
		return qcup_AcquiringInstitutionIdentificationCode;
	}

	public void setQCUP_AcquiringInstitutionIdentificationCode(
			String qCUP_AcquiringInstitutionIdentificationCode) {
		qcup_AcquiringInstitutionIdentificationCode = qCUP_AcquiringInstitutionIdentificationCode;
	}

	public String getQCUP_ForwardingInstitutionIdentificationCode() {
		return qcup_ForwardingInstitutionIdentificationCode;
	}

	public void setQCUP_ForwardingInstitutionIdentificationCode(
			String qCUP_ForwardingInstitutionIdentificationCode) {
		qcup_ForwardingInstitutionIdentificationCode = qCUP_ForwardingInstitutionIdentificationCode;
	}

	public String getQCUP_Track2Data() {
		return qcup_Track2Data;
	}

	public void setQCUP_Track2Data(String qCUP_Track2Data) {
		qcup_Track2Data = qCUP_Track2Data;
	}

	public String getQCUP_Track3Data() {
		return qcup_Track3Data;
	}

	public void setQCUP_Track3Data(String qCUP_Track3Data) {
		qcup_Track3Data = qCUP_Track3Data;
	}

	public String getQCUP_RetrievalReferenceNumber() {
		return qcup_RetrievalReferenceNumber;
	}

	public void setQCUP_RetrievalReferenceNumber(
			String qCUP_RetrievalReferenceNumber) {
		qcup_RetrievalReferenceNumber = qCUP_RetrievalReferenceNumber;
	}

	public String getQCUP_AuthorizationIdentificationResponse() {
		return qcup_AuthorizationIdentificationResponse;
	}

	public void setQCUP_AuthorizationIdentificationResponse(
			String qCUP_AuthorizationIdentificationResponse) {
		qcup_AuthorizationIdentificationResponse = qCUP_AuthorizationIdentificationResponse;
	}

	public String getQCUP_ResponseCode() {
		return qcup_ResponseCode;
	}

	public void setQCUP_ResponseCode(String qCUP_ResponseCode) {
		qcup_ResponseCode = qCUP_ResponseCode;
	}

	public String getQCUP_CardAcceptorTerminalIdentification() {
		return qcup_CardAcceptorTerminalIdentification;
	}

	public void setQCUP_CardAcceptorTerminalIdentification(
			String qCUP_CardAcceptorTerminalIdentification) {
		qcup_CardAcceptorTerminalIdentification = qCUP_CardAcceptorTerminalIdentification;
	}

	public String getQCUP_CardAcceptorIdentificationCode() {
		return qcup_CardAcceptorIdentificationCode;
	}

	public void setQCUP_CardAcceptorIdentificationCode(
			String qCUP_CardAcceptorIdentificationCode) {
		qcup_CardAcceptorIdentificationCode = qCUP_CardAcceptorIdentificationCode;
	}

	public String getQCUP_CardAcceptorNameLocation() {
		return qcup_CardAcceptorNameLocation;
	}

	public void setQCUP_CardAcceptorNameLocation(
			String qCUP_CardAcceptorNameLocation) {
		qcup_CardAcceptorNameLocation = qCUP_CardAcceptorNameLocation;
	}

	public String getQCUP_AdditionalDataPrivate() {
		return qcup_AdditionalDataPrivate;
	}

	public void setQCUP_AdditionalDataPrivate(String qCUP_AdditionalDataPrivate) {
		qcup_AdditionalDataPrivate = qCUP_AdditionalDataPrivate;
	}

	public String getQCUP_CurrencyCodeTransaction() {
		return qcup_CurrencyCodeTransaction;
	}

	public void setQCUP_CurrencyCodeTransaction(String qCUP_CurrencyCodeTransaction) {
		qcup_CurrencyCodeTransaction = qCUP_CurrencyCodeTransaction;
	}

	public byte[] getQCUP_pinData() {
		return qcup_pinData;
	}

	public void setQCUP_pinData(byte[] qCUP_pinData) {
		qcup_pinData = qCUP_pinData;
	}

	public String getQCUP_SecurityRelatedControlInformation() {
		return qcup_SecurityRelatedControlInformation;
	}

	public void setQCUP_SecurityRelatedControlInformation(
			String qCUP_SecurityRelatedControlInformation) {
		qcup_SecurityRelatedControlInformation = qCUP_SecurityRelatedControlInformation;
	}

	public String getQCUP_AdditionalAmounts() {
		return qcup_AdditionalAmounts;
	}

	public void setQCUP_AdditionalAmounts(String qCUP_AdditionalAmounts) {
		qcup_AdditionalAmounts = qCUP_AdditionalAmounts;
	}

	public byte[] getQCUP_IntegratedCircuitCardSystemRelatedData() {
		return qcup_IntegratedCircuitCardSystemRelatedData;
	}

	public void setQCUP_IntegratedCircuitCardSystemRelatedData(
			byte[] qCUP_IntegratedCircuitCardSystemRelatedData) {
		qcup_IntegratedCircuitCardSystemRelatedData = qCUP_IntegratedCircuitCardSystemRelatedData;
	}

	public String getQCUP_DetailInquiring() {
		return qcup_DetailInquiring;
	}

	public void setQCUP_DetailInquiring(String qCUP_DetailInquiring) {
		qcup_DetailInquiring = qCUP_DetailInquiring;
	}

	public String getQCUP_AdditionalPointOfServiceInformation() {
		return qcup_AdditionalPointOfServiceInformation;
	}

	public void setQCUP_AdditionalPointOfServiceInformation(
			String qCUP_AdditionalPointOfServiceInformation) {
		qcup_AdditionalPointOfServiceInformation = qCUP_AdditionalPointOfServiceInformation;
	}

	public String getQCUP_AuthenticationInformation() {
		return qcup_AuthenticationInformation;
	}

	public void setQCUP_AuthenticationInformation(
			String qCUP_AuthenticationInformation) {
		qcup_AuthenticationInformation = qCUP_AuthenticationInformation;
	}

	public byte[] getQCUP_ReservedPrivate() {
		return qcup_ReservedPrivate;
	}

	public void setQCUP_ReservedPrivate(byte[] qCUP_ReservedPrivate) {
		qcup_ReservedPrivate = qCUP_ReservedPrivate;
	}

	public String getQCUP_Networkmanagementcode() {
		return qcup_Networkmanagementcode;
	}

	public void setQCUP_Networkmanagementcode(String qCUP_Networkmanagementcode) {
		qcup_Networkmanagementcode = qCUP_Networkmanagementcode;
	}

	public String getQCUP_OriginalDataElements() {
		return qcup_OriginalDataElements;
	}

	public void setQCUP_OriginalDataElements(String qCUP_OriginalDataElements) {
		qcup_OriginalDataElements = qCUP_OriginalDataElements;
	}

	public byte[] getQCUP_MessageSecurityCode() {
		return qcup_MessageSecurityCode;
	}

	public void setQCUP_MessageSecurityCode(byte[] qCUP_MessageSecurityCode) {
		qcup_MessageSecurityCode = qCUP_MessageSecurityCode;
	}

	public String getQCUP_DestinationInstituitionIdentificationCode() {
		return qcup_DestinationInstituitionIdentificationCode;
	}

	public void setQCUP_DestinationInstituitionIdentificationCode(
			String qCUP_DestinationInstituitionIdentificationCode) {
		qcup_DestinationInstituitionIdentificationCode = qCUP_DestinationInstituitionIdentificationCode;
	}

	public String getQCUP_AccountIdentification1() {
		return qcup_AccountIdentification1;
	}

	public void setQCUP_AccountIdentification1(String qCUP_AccountIdentification1) {
		qcup_AccountIdentification1 = qCUP_AccountIdentification1;
	}

	public String getQCUP_AccountIdentification2() {
		return qcup_AccountIdentification2;
	}

	public void setQCUP_AccountIdentification2(String qCUP_AccountIdentification2) {
		qcup_AccountIdentification2 = qCUP_AccountIdentification2;
	}

	public byte[] getQCUP_Mac() {
		return qcup_Mac;
	}

	public void setQCUP_Mac(byte[] qCUP_Mac) {
		qcup_Mac = qCUP_Mac;
	}

	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}

	public String getServiceResponse() {
		return serviceResponse;
	}

	public void setServiceResponse(String serviceResponse) {
		this.serviceResponse = serviceResponse;
	}

	public byte[] getServiceRequestCode() {
		return serviceRequestCode;
	}

	public void setServiceRequestCode(byte[] serviceRequestCode) {
		this.serviceRequestCode = serviceRequestCode;
	}

	public byte[] getServiceRequestPara() {
		return serviceRequestPara;
	}

	public void setServiceRequestPara(byte[] serviceRequestPara) {
		this.serviceRequestPara = serviceRequestPara;
	}

	public byte[] getServiceRequestSign() {
		return serviceRequestSign;
	}

	public void setServiceRequestSign(byte[] serviceRequestSign) {
		this.serviceRequestSign = serviceRequestSign;
	}

	
	public byte[] getServiceScript() {
		return serviceScript;
	}

	public void setServiceScript(byte[] serviceScript) {
		this.serviceScript = serviceScript;
	}

	public String getTPDU() {
		return TPDU;
	}

	public void setTPDU(String tpdu) {
		TPDU = tpdu;
	}

	public String getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(String balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public String getNetManageCode() {
		return netManageCode;
	}
	
	public void setNetManageCode(String netManageCode) {
		this.netManageCode = netManageCode;
	}

	public String getOperatorCode() {
		return operatorCode;
	}


	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}



	public String getAuthorizationIdentifyResCode() {
		return authorizationIdentifyResCode;
	}


	public void setAuthorizationIdentifyResCode(String authorizationIdentifyResCode) {
		this.authorizationIdentifyResCode = authorizationIdentifyResCode;
	}


	public String getChinaUnionPayInfo() {
		return chinaUnionPayInfo;
	}


	public void setChinaUnionPayInfo(String chinaUnionPayInfo) {
		this.chinaUnionPayInfo = chinaUnionPayInfo;
	}


	public String getExtInfo() {
		return extInfo;
	}


	public void setExtInfo(String extInfo) {
		this.extInfo = extInfo;
	}


	public String getInternationalOrganizationCode() {
		return internationalOrganizationCode;
	}


	public void setInternationalOrganizationCode(
			String internationalOrganizationCode) {
		this.internationalOrganizationCode = internationalOrganizationCode;
	}


	public String getPublishBank() {
		return publishBank;
	}


	public void setPublishBank(String publishBank) {
		this.publishBank = publishBank;
	}


	public String getReceiveBank() {
		return receiveBank;
	}


	public void setReceiveBank(String receiveBank) {
		this.receiveBank = receiveBank;
	}



	public String getSettlementdate() {
		return settlementdate;
	}


	public void setSettlementdate(String settlementdate) {
		this.settlementdate = settlementdate;
	}

	public byte[] getParameterInfo() {
		return parameterInfo;
	}

	public void setParameterInfo(byte[] parameterInfo) {
		this.parameterInfo = parameterInfo;
	}

	public String getAcceptorSign() {
		return acceptorSign;
	}


	public void setAcceptorSign(String acceptorSign) {
		this.acceptorSign = acceptorSign;
	}


	public String getRetrievalRefNum() {
		return retrievalRefNum;
	}


	public void setRetrievalRefNum(String retrievalRefNum) {
		this.retrievalRefNum = retrievalRefNum;
	}


	public byte[] getClientKey() {
		return clientKey;
	}


	public void setClientKey(byte[] clientKey) {
		this.clientKey = clientKey;
	}

	

	public String getOriginalBatchNumber() {
		return originalBatchNumber;
	}

	public void setOriginalBatchNumber(String originalBatchNumber) {
		this.originalBatchNumber = originalBatchNumber;
	}

	public String getOriginalTraceNumber() {
		return originalTraceNumber;
	}

	public void setOriginalTraceNumber(String originalTraceNumber) {
		this.originalTraceNumber = originalTraceNumber;
	}

	public String getOriginalTransactionDate() {
		return originalTransactionDate;
	}

	public void setOriginalTransactionDate(String originalTransactionDate) {
		this.originalTransactionDate = originalTransactionDate;
	}
	
	public String getCardValidate() {
		return cardValidate;
	}
	public void setCardValidate(String cardValidate) {
		this.cardValidate = cardValidate;
	}
	public String getCurrencyTransactionCode() {
		return currencyTransactionCode;
	}
	public void setCurrencyTransactionCode(String currencyTransactionCode) {
		this.currencyTransactionCode = currencyTransactionCode;
	}
	public byte[] getMac() {
		return mac;
	}
	public void setMac(byte[] mac) {
		this.mac = mac;
	}
	public byte[] getPinData() {
		return pinData;
	}
	public void setPinData(byte[] pinData) {
		this.pinData = pinData;
	}
	public String getPointServiceCode() {
		return pointServiceCode;
	}
	public void setPointServiceCode(String pointServiceCode) {
		this.pointServiceCode = pointServiceCode;
	}
	public String getPointServiceInputMode() {
		return pointServiceInputMode;
	}
	public void setPointServiceInputMode(String pointServiceInputMode) {
		this.pointServiceInputMode = pointServiceInputMode;
	}
	public String getPointServicePINCode() {
		return pointServicePINCode;
	}
	public void setPointServicePINCode(String pointServicePINCode) {
		this.pointServicePINCode = pointServicePINCode;
	}
	public String getPriAccountNumber() {
		return priAccountNumber;
	}
	public void setPriAccountNumber(String priAccountNumber) {
		this.priAccountNumber = priAccountNumber;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public String getSecurityControlInfo() {
		return securityControlInfo;
	}
	public void setSecurityControlInfo(String securityControlInfo) {
		this.securityControlInfo = securityControlInfo;
	}
	public String getTrack2Data() {
		return track2Data;
	}
	public void setTrack2Data(String track2Data) {
		this.track2Data = track2Data;
	}
	public String getTrack3Data() {
		return track3Data;
	}
	public void setTrack3Data(String track3Data) {
		this.track3Data = track3Data;
	}
	public String getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	

	
	
	/**
	 * @brief
	 * 受卡方系统跟踪号
	 * 
	 * @return
	 */
	public int getTraceNumber() {
		return Integer.parseInt(traceNumber);
	}


	/**
	 * @brief
	 * 设置受卡方系统跟踪号
	 * 
	 * @param traceNumber
	 */
	public void setTraceNumber(String traceNumber) {
		this.traceNumber = traceNumber;
	}
	
	/**
	 * @brief
	 * 设置受卡方系统跟踪号
	 * 
	 * @param traceNumber
	 */
	public void setTraceNumber(int traceNumber) {
		this.traceNumber = String.valueOf(traceNumber);
	}
	
	
	/**
	 * @brief
	 * 受卡机终端标识码
	 * 
	 * @return
	 */
	public String getServiceCode() {
		return serviceCode;
	}


	/**
	 * @brief
	 * 设置受卡机终端标识码
	 * 
	 * @param serviceCode
	 */
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	

	/**
	 * @brief
	 * 受卡方标识码 商户编号
	 * 
	 * @return
	 */
	public String getCardAcptIdenCode() {
		return cardAcptIdenCode;
	}


	/**
	 * @brief
	 * 设置受卡方标识码 商户编号
	 * 
	 * @param cardAcptIdenCode
	 */
	public void setCardAcptIdenCode(String cardAcptIdenCode) {
		this.cardAcptIdenCode = cardAcptIdenCode;
	}
	
	
	/**
	 * @return cardAcptAddress
	 */
	
	public String getCardAcptAddress() {
		return cardAcptAddress;
	}

	/** 
	 * @param cardAcptAddress 要设置的 cardAcptAddress 
	 */
	public void setCardAcptAddress(String cardAcptAddress) {
		this.cardAcptAddress = cardAcptAddress;
	}

	/**
	 * @brief
	 * 获得应答码
	 * 
	 * @return
	 */
	public String getResponseCode() {
		return responseCode;
	}


	/**
	 * @brief
	 * 设置应答码
	 * 
	 * @param responseCode
	 */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getLocalTransTime() {
		return localTransTime;
	}


	public void setLocalTransTime(String localTransTime) {
		this.localTransTime = localTransTime;
	}


	public String getLocalTransDate() {
		return localTransDate;
	}


	public void setLocalTransDate(String localTransDate) {
		this.localTransDate = localTransDate;
	}

	
	/**
	 * @brief
	 * 批次号
	 * 
	 * @return
	 */
	public int getBatchNumber() {
		return Integer.parseInt(batchNumber);
	}
	
	/**
	 * @brief
	 * 设置批次号
	 * 
	 * @param batchNumber
	 */
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	
	/**
	 * @brief
	 * 设置批次号
	 * 
	 * @param batchNumber
	 */
	public void setBatchNumber(int batchNumber) {
		this.batchNumber = String.valueOf(batchNumber);
	}
	
	/**
	 * @brief
	 * 交易类型
	 * 
	 * @return
	 */
//	public abstract String getTransactionType();
	
	/**
	 * @brief
	 * 设置交易类型
	 * 
	 * @param transactionType
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getMsgTypeID() {
		return msgTypeID;
	}

	public void setMsgTypeID(String msgTypeID) {
		this.msgTypeID = msgTypeID;
	}

	public String getForeignCardCreditTotalAmount() {
		return foreignCardCreditTotalAmount;
	}

	public void setForeignCardCreditTotalAmount(String foreignCardCreditTotalAmount) {
		this.foreignCardCreditTotalAmount = foreignCardCreditTotalAmount;
	}

	public String getForeignCardCreditTotalCount() {
		return foreignCardCreditTotalCount;
	}

	public void setForeignCardCreditTotalCount(String foreignCardCreditTotalCount) {
		this.foreignCardCreditTotalCount = foreignCardCreditTotalCount;
	}

	public String getForeignCardLoanTotalAmount() {
		return foreignCardLoanTotalAmount;
	}

	public void setForeignCardLoanTotalAmount(String foreignCardLoanTotalAmount) {
		this.foreignCardLoanTotalAmount = foreignCardLoanTotalAmount;
	}

	public String getForeignCardLoanTotalCount() {
		return foreignCardLoanTotalCount;
	}

	public void setForeignCardLoanTotalCount(String foreignCardLoanTotalCount) {
		this.foreignCardLoanTotalCount = foreignCardLoanTotalCount;
	}



	public String getLocalCardCreditTotalAmount() {
		return localCardCreditTotalAmount;
	}

	public void setLocalCardCreditTotalAmount(String localCardCreditTotalAmount) {
		this.localCardCreditTotalAmount = localCardCreditTotalAmount;
	}

	public String getLocalCardCreditTotalCount() {
		return localCardCreditTotalCount;
	}

	public void setLocalCardCreditTotalCount(String localCardCreditTotalCount) {
		this.localCardCreditTotalCount = localCardCreditTotalCount;
	}

	public String getLocalCardLoanTotalAmount() {
		return localCardLoanTotalAmount;
	}

	public void setLocalCardLoanTotalAmount(String localCardLoanTotalAmount) {
		this.localCardLoanTotalAmount = localCardLoanTotalAmount;
	}

	public String getLocalCardLoanTotalCount() {
		return localCardLoanTotalCount;
	}

	public void setLocalCardLoanTotalCount(String localCardLoanTotalCount) {
		this.localCardLoanTotalCount = localCardLoanTotalCount;
	}

	public String getForeignCardCheckAccountCode() {
		return foreignCardCheckAccountCode;
	}

	public void setForeignCardCheckAccountCode(String foreignCardCheckAccountCode) {
		this.foreignCardCheckAccountCode = foreignCardCheckAccountCode;
	}

	public String getLocalCardCheckAccountCode() {
		return localCardCheckAccountCode;
	}

	public void setLocalCardCheckAccountCode(String localCardCheckAccountCode) {
		this.localCardCheckAccountCode = localCardCheckAccountCode;
	}

	public String getTransactionDetial() {
		return transactionDetial;
	}

	public void setTransactionDetial(String transactionDetial) {
		this.transactionDetial = transactionDetial;
	}

	public String getAuthorizationOrganization() {
		return authorizationOrganization;
	}

	public void setAuthorizationOrganization(String authorizationOrganization) {
		this.authorizationOrganization = authorizationOrganization;
	}

	public String getAuthorizationType() {
		return authorizationType;
	}

	public void setAuthorizationType(String authorizationType) {
		this.authorizationType = authorizationType;
	}
	public int getTransactionType() {
		return Integer.parseInt(transactionType);
	}

	/**
	 * 原授权码.在预授权完成联机的时候由EFTModoule进行设置!打印的时候需要使用
	 */
	private String originalAuthorizationCode;
	
	public String getOriginalAuthorizationCode() {
		return originalAuthorizationCode;
	}

	public void setOriginalAuthorizationCode(String originalAuthorizationCode) {
		this.originalAuthorizationCode = originalAuthorizationCode;
	}

	/**
	 * 原系统参考号.在退货的时候由EFTModoule进行设置!打印的时候需要使用
	 */
	private String originalRetrievalRef;

	public String cardHolderName;

	public String idType;

	public String idNumber;

	public String mobileNumber;

	public String smartCardSerialNumber;
	
	public String getOriginalRetrievalRef() {
		return originalRetrievalRef;
	}

	public void setOriginalRetrievalRef(String originalRetrievalRef) {
		this.originalRetrievalRef = originalRetrievalRef;
	}

	public int getUniqueID() {
		return uniqueID;
	}

	public void setUniqueID(int uniqueID) {
		this.uniqueID = uniqueID;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	/**
	 * @return the cardHolderName
	 */
	public String getCardHolderName() {
		return cardHolderName;
	}

	/**
	 * @param cardHolderName the cardHolderName to set
	 */
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	/**
	 * @return the idNumber
	 */
	public String getIdNumber() {
		return idNumber;
	}

	/**
	 * @param idNumber the idNumber to set
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	/**
	 * @return the idType
	 */
	public String getIdType() {
		return idType;
	}

	/**
	 * @param idType the idType to set
	 */
	public void setIdType(String idType) {
		this.idType = idType;
	}

	/**
	 * @return the mobileNumber
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	/**
	 * @return the smartCardSerialNumber
	 */
	public String getSmartCardSerialNumber() {
		return smartCardSerialNumber;
	}

	/**
	 * @param smartCardSerialNumber the smartCardSerialNumber to set
	 */
	public void setSmartCardSerialNumber(String smartCardSerialNumber) {
		this.smartCardSerialNumber = smartCardSerialNumber;
	}
}
