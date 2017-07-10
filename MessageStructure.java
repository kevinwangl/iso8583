/**
 *
 * Revision 1.10  2007/12/14 06:57:04  wangl
 * 预授权追加报文格式修改
 *
 */

import java.util.Hashtable;


/**
 *	@brief
 *   <p><b>消息结构定义</b></p>
 * 
 *  &nbsp;&nbsp;&nbsp;&nbsp; 消息结构定义
 * 
 *  @author wangl
 *  @version eJPos SDK1.0
 *  @see 
 *  @since 2007-9-6
 */

public class MessageStructure {
	
	/**
	 * POS消息结构
	 */
	@SuppressWarnings("rawtypes")
	private static final Hashtable map = new Hashtable();
	private static final AField FIELD_11 = new BCDField(11,"受卡方系统跟踪号",6, true,"traceNumber",false);
	private static final AField FIELD_12 = new BCDField(12,"受卡方所在地时间",  6, true, "localTransTime",false);
	private static final AField FIELD_13 = new BCDField(13,"受卡方所在地日期",  4, true, "localTransDate",false);
	private static final AField FIELD_32 = new MutableBCDField(32,"受理方标识码", 11, true, "acceptorSign",2,false);
	private static final AField FIELD_37 = new ASCIIField(37,"检索参考号", 12, true, "retrievalRefNum",false);
	private static final AField FIELD_39 = new ASCIIField(39,"应答码",  2, true, "responseCode",false);
	private static final AField FIELD_41 = new ASCIIField(41,"受卡机终端标识码",  8, true, "serviceCode",false);
	private static final AField FIELD_42 = new ASCIIField(42,"受卡方标识码", 15, true, "cardAcptIdenCode",false);
	private static final AField FIELD_43 = new ASCIIField(43,"受卡方名称地址", 40, true, "cardAcptAddress",false);
	private static final AField FIELD_62_Key = new MutableBinaryField(62,"终端密钥", 24, false, "clientKey",3,false);
	private static final AField FIELD_62_Parameter = new MutableASCIIField(62,"终端参数信息", 160, true, "parameterInfo",3,false);
	private static final AField FIELD_62_status = new MutableASCIIField(62,"终端状态信息", 160, true, "parameterInfo",3,false);
	private static final AField FIELD_62_Identity = new MutableASCIIField(62,"身份认证信息", 22, false, "parameterInfo",3,false);
	private static final AField FIELD_60_1 = new BCDField(1,"交易类型码", 2, true, "transactionType",false);
	private static final AField FIELD_60_2 = new BCDField(2, "批次号", 6, true, "batchNumber",false);
	private static final AField FIELD_60_3 = new BCDField(3,"网络管理信息码",3, true, "netManageCode",false);
	private static final AField FIELD_OPCODE = new ASCIIField(1,  "操作员代码", 3, false, "operatorCode",false);
	private static final AField FIELD_2 = new MutableBCDField(2, "主帐号", 19, false, "priAccountNumber",2,false);
	private static final AField FIELD_3 = new BCDField(3, "交易处理码", 6, true, "process",false);
	private static final AField FIELD_4= new BCDField(4, "交易金额",12, true, "transactionAmount",ISO8583Util.PREFIX,false);
	private static final AField FIELD_14= new BCDField(14, "卡有效期", 4, false, "cardValidate",false);
	private static final AField FIELD_22= new BCDField(22, "服务点输入方式", 3, true, "pointServiceInputMode",false);
	private static final AField FIELD_25= new BCDField(25, "服务点条件码", 2, true, "pointServiceCode",false);
	private static final AField FIELD_26= new BCDField(26, "服务点PIN获取码", 2, false, "pointServicePINCode",false);
	private static final AField FIELD_35= new MutableBCDField(35, "2磁道数据", 37, true, "track2Data",2,false);
	private static final AField FIELD_36= new MutableBCDField(36, "3磁道数据", 104, true, "track3Data",3,false);
	private static final AField FIELD_44= new MutableASCIIField(44, "附加响应数据", 19, true, "extInfo",2,false);
	private static final AField FIELD_49= new ASCIIField(49, "交易货币代码", 3, true, "currencyTransactionCode",false);
	private static final AField FIELD_52= new BinaryField(52, "个人标识数据码", 8, false, "pinData",false);
	private static final AField FIELD_53= new BCDField(53, "安全控制信息", 16, false, "securityControlInfo",false);
	private static final AField FIELD_64= new BinaryField(64, "消息鉴别码", 8, true, "mac",false);
	private static final AField FIELD_38= new ASCIIField(38, "授权应答码", 6, false, "authorizationIdentifyResCode",false);
	private static final AField FIELD_15= new BCDField(15, "清算日期", 4, false, "settlementdate",false);
	private static final AField FIELD_63_1 = new ASCIIField(1, "国际组织代码", 3, true, "internationalOrganizationCode",false);
	private static final AField FIELD_63_2 = new ASCIIField(2, "发卡行返回信息", 20, false, "publishBank",false);
	private static final AField FIELD_63_3 = new ASCIIField(3, "中国银联返回信息", 20,  false, "chinaUnionPayInfo",false);
	private static final AField FIELD_63_4 = new ASCIIField(4, "收单行返回信息", 20,  false, "receiveBank",false);
	private static final AField FIELD_61_1 = new BCDField(1,  "原交易批次号", 6, false, "originalBatchNumber",false);
	private static final AField FIELD_61_2 = new BCDField(2,  "原POS流水号", 6, false, "originalTraceNumber",false);
	private static final AField FIELD_61_3 = new BCDField(3,  "原交易日期", 4, false, "originalTransactionDate",false);
	private static final AField FIELD_61_4 = new BCDField(4,  "原授权方式", 2, false, "authorizationType",false);
	private static final AField FIELD_61_5 = new BCDField(5, "原授权机构", 11, false, "authorizationOrganization",false);
	private static final AField FIELD_54 = new MutableASCIIField(54,"附加金额",20,true,"balanceAmount",3,false);
	private static final AField FIELD_48_LocalCardLoanTotalAmount =  new BCDField(1,"内卡借记总金额", 12, true, "localCardLoanTotalAmount",false);
	private static final AField FIELD_48_LocalCardLoanTotalCount =  new BCDField(2,  "内卡借记总笔数", 3, true, "localCardLoanTotalCount",false);
	private static final AField FIELD_48_LocalCardCreditTotalAmount =  new BCDField(3,  "内卡贷记总金额", 12, true, "localCardCreditTotalAmount",false);
	private static final AField FIELD_48_LocalCardCreditTotalCount =  new BCDField(4, "内卡贷记总笔数", 3, true, "localCardCreditTotalCount",false);
	private static final AField FIELD_48_LocalCardCheckAccountCode =  new BCDField(5, "内卡对账应答码", 1, true, "localCardCheckAccountCode",false);
	private static final AField FIELD_48_ForeignCardLoanTotalAmount =  new BCDField(6, "外卡借记总金额", 12, true, "foreignCardLoanTotalAmount",false);
	private static final AField FIELD_48_ForeignCardLoanTotalCount =  new BCDField(7,"外卡借记总笔数", 3, true, "foreignCardLoanTotalCount",false);
	private static final AField FIELD_48_ForeignCardCreditTotalAmount =  new BCDField(8,"外卡贷记总金额", 12, true, "foreignCardCreditTotalAmount",false);
	private static final AField FIELD_48_ForeignCardCreditTotalCount =  new BCDField(9,"外卡贷记总笔数", 3, true, "foreignCardCreditTotalCount",false);
	private static final AField FIELD_48_ForeignCardCheckAccountCode =  new BCDField(10, "外卡对账应答码", 1, true, "foreignCardCheckAccountCode",false);
	private static final AField FIELD_48_TIP_AMOUNT = new MutableBCDField(48,"小费金额", 62, false, "transactionDetial",3,false);
	private static final AField FIELD_48_DETIAL = new MutableBCDField(48, "交易明細", 322, false, "transactionDetial",3,false);
	private static final AField FIELD_GROUP_60_1_2_3 = new BCDGroupField(60,11,3,new AField[]{FIELD_60_1,FIELD_60_2,FIELD_60_3,},false);
	private static final AField FIELD_GROUP_61_1_2 = new BCDGroupField(61,12,3,new AField[]{FIELD_61_1,FIELD_61_2},false);
	private static final AField FIELD_GROUP_48_TOTAL = new BCDGroupField(48,62,3,new AField[]{FIELD_48_LocalCardLoanTotalAmount,FIELD_48_LocalCardLoanTotalCount,FIELD_48_LocalCardCreditTotalAmount,FIELD_48_LocalCardCreditTotalCount,FIELD_48_LocalCardCheckAccountCode,FIELD_48_ForeignCardLoanTotalAmount,FIELD_48_ForeignCardLoanTotalCount,FIELD_48_ForeignCardCreditTotalAmount,FIELD_48_ForeignCardCreditTotalCount,FIELD_48_ForeignCardCheckAccountCode},false);
	private static final AField FIELD_GROUP_60_1_2 = new BCDGroupField(60,8,3,new AField[]{FIELD_60_1,FIELD_60_2},false);
	private static final AField FIELD_GROUP_63_1_2_3_4 = new ASCIIGroupField(63,63,3,new AField[]{FIELD_63_1,FIELD_63_2,FIELD_63_3,FIELD_63_4},false);
	private static final AField FIELD_GROUP_63_OPCODE = new ASCIIGroupField(63,3,3,new AField[]{FIELD_OPCODE},false);
	private static final AField FIELD_GROUP_63_InternationalOrganizationCode = new ASCIIGroupField(63,3,3,new AField[]{FIELD_63_1},false);
	private static final AField FIELD_GROUP_61_1_2_3 = new BCDGroupField(61,29,3,new AField[]{FIELD_61_1,FIELD_61_2,FIELD_61_3},false);
	private static final AField FIELD_GROUP_61_1_2_3_4_5 = new BCDGroupField(61,29,3,new AField[]{FIELD_61_1,FIELD_61_2,FIELD_61_3,FIELD_61_4,FIELD_61_5},false);
	
	
	public static final AField[] SingInReqMsg = new AField[]{
		FIELD_11,
		FIELD_41,
		FIELD_42,
		FIELD_GROUP_60_1_2_3,
		FIELD_GROUP_63_OPCODE
	};
	
	public static final AField[] SingInResMsg = new AField[]{
		FIELD_11,
		FIELD_12,
		FIELD_13,
		FIELD_32,
		FIELD_37,
		FIELD_39,				
		FIELD_41,
		FIELD_42,
		FIELD_GROUP_60_1_2_3,
		FIELD_62_Key
	};
	
	public static final AField[] SingOutResMsg = new AField[]{
		FIELD_11,
		FIELD_12,
		FIELD_13,
		FIELD_32,
		FIELD_37,
		FIELD_39,				
		FIELD_41,
		FIELD_42,
		FIELD_GROUP_60_1_2_3,
	};
	
	public static final AField[] SingOutReqMsg = new AField[]{
		FIELD_11,	
		FIELD_41,
		FIELD_42,
		FIELD_GROUP_60_1_2_3,
	};
	
	public static final AField[] ParameterTransResMsg = new AField[]{
		FIELD_12,
		FIELD_13,
		FIELD_37,
		FIELD_39,	
		FIELD_41,
		FIELD_42,
		FIELD_GROUP_60_1_2_3,
		FIELD_62_Parameter
	};
	
	
	public static final AField[] ParameterTransReqMsg = new AField[]{
		FIELD_41,
		FIELD_42,
		FIELD_GROUP_60_1_2_3,
		
	};
	
	public static final AField[] ResponseTestingResMsg = new AField[]{
		FIELD_12,
		FIELD_13,
		FIELD_39,
		FIELD_41,
		FIELD_42,
		FIELD_GROUP_60_1_2_3,
	};
	
	
	public static final AField[] ResponseTestingReqMsg = new AField[]{
		FIELD_41,
		FIELD_42,
		FIELD_GROUP_60_1_2_3,
	};
	
	
	public static final AField[] StatusReportResMsg = new AField[]{
		FIELD_12,
		FIELD_13,
		FIELD_39,
		FIELD_41,
		FIELD_42,
		FIELD_GROUP_60_1_2_3,
	};
	
	
	
	public static final AField[] StatusReportReqMsg = new AField[]{
		FIELD_41,
		FIELD_42,
		FIELD_GROUP_60_1_2_3,
		FIELD_62_status,
	};
	
	public static final AField[] PurchaseResMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_12,
		FIELD_13,
		FIELD_14,
		FIELD_15,
		FIELD_25,
		FIELD_32,
		FIELD_37,
		FIELD_38,
		FIELD_39,
		FIELD_41,
		FIELD_42,
		FIELD_44,
		FIELD_49,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		FIELD_GROUP_63_1_2_3_4,
		FIELD_64
	};
	
	public static final AField[] PurchaseReqMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_14,
		FIELD_22,
		FIELD_25,
		FIELD_26,
		FIELD_35,
		FIELD_36,
		FIELD_41,
		FIELD_42,
		FIELD_49,
		FIELD_52,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		
		FIELD_64
	};
	
	
	public static final AField[] PreAuthorizationResMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_12,
		FIELD_13,
		FIELD_14,
		FIELD_15,
		FIELD_25,
		FIELD_32,
		FIELD_37,
		FIELD_38,
		FIELD_39,
		FIELD_41,
		FIELD_42,
		FIELD_44,
		FIELD_49,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		FIELD_GROUP_63_1_2_3_4,
		FIELD_64
		
	};
	
	
	
	public static final AField[] PreAuthorizationReqMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_14,
		FIELD_22,
		FIELD_25,
		FIELD_26,
		FIELD_35,
		FIELD_36,
		FIELD_41,
		FIELD_42,
		FIELD_49,
		FIELD_52,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		FIELD_64
		
	};
	
	
	
	public static final AField[] PreAuthorizationIncreaseResMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_12,
		FIELD_13,
		FIELD_14,
		FIELD_15,
		FIELD_25,
		FIELD_32,
		FIELD_37,
		FIELD_38,
		FIELD_39,
		FIELD_41,
		FIELD_42,
		FIELD_44,
		FIELD_49,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		FIELD_GROUP_63_1_2_3_4,
		FIELD_64
		
	};
	
	
	
	public static final AField[] PreAuthorizationIncreaseReqMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_14,
		FIELD_22,
		FIELD_25,
		FIELD_26,
		FIELD_35,
		FIELD_36,
		FIELD_41,
		FIELD_42,
		FIELD_49,
		FIELD_52,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		FIELD_64
		
	};
	
	
	
	public static final AField[] PreAuthorizationIncreaseReversalReqMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_14,
		FIELD_22,
		FIELD_25,
		FIELD_35,
		FIELD_36,
		FIELD_38,
		FIELD_39,
		FIELD_41,
		FIELD_42,
		FIELD_49,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		FIELD_64
		
	}; 
	
	
	public static final AField[] PreAuthorizationIncreaseReversalResMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_12,
		FIELD_13,
		FIELD_14,
		FIELD_15,
		FIELD_25,
		FIELD_32,
		FIELD_37,
		FIELD_39,
		FIELD_41,
		FIELD_42,
		FIELD_49,
		FIELD_53,
		FIELD_54,
		FIELD_GROUP_60_1_2,
		FIELD_64
		
	}; 
	
	public static final AField[] PreAuthorizationCompleteCancelReversalReqMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_14,
		FIELD_22,
		FIELD_25,
		FIELD_35,
		FIELD_36,
		FIELD_38,
		FIELD_39,
		FIELD_41,
		FIELD_42,
		FIELD_49,
		FIELD_53,
		FIELD_GROUP_61_1_2_3,
		FIELD_GROUP_60_1_2,
		FIELD_64
		
	};
	
	public static final AField[] PreAuthorizationCompleteCancelReversalResMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_12,
		FIELD_13,
		FIELD_14,
		FIELD_15,
		FIELD_25,
		FIELD_32,
		FIELD_37,
		FIELD_39,
		FIELD_41,
		FIELD_42,
		FIELD_49,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		FIELD_64
		
	};
	
	
	
	public static final AField[] PreAuthorizationCompleteCancelReqMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_14,
		FIELD_15,
		FIELD_22,
		FIELD_25,
		FIELD_26,
		FIELD_35,
		FIELD_36,
		FIELD_37,
		FIELD_38,
		FIELD_41,
		FIELD_42,
		FIELD_49,
		FIELD_52,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		FIELD_GROUP_61_1_2_3,
		FIELD_64
	};
	
	public static final AField[] PreAuthorizationCompleteCancelResMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_12,
		FIELD_13,
		FIELD_14,
		FIELD_15,
		FIELD_25,
		FIELD_32,
		FIELD_37,
		FIELD_38,
		FIELD_39,
		FIELD_41,
		FIELD_42,
		FIELD_44,
		FIELD_49,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		FIELD_GROUP_63_1_2_3_4,
		FIELD_64
		
	};
	
	

	
	public static final AField[] PreAuthorizationCancelReqMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_14,
		FIELD_22,
		FIELD_25,
		FIELD_26,
		FIELD_35,
		FIELD_36,
		FIELD_38,
		FIELD_41,
		FIELD_42,
		FIELD_49,
		FIELD_52,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		FIELD_GROUP_61_1_2_3,
		FIELD_64
		
	}; 
	
	
	public static final AField[] PreAuthorizationCancelResMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_12,
		FIELD_13,
		FIELD_14,
		FIELD_15,
		FIELD_25,
		FIELD_32,
		FIELD_37,
		FIELD_38,
		FIELD_39,
		FIELD_41,
		FIELD_42,
		FIELD_44,
		FIELD_49,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		FIELD_GROUP_63_1_2_3_4,
		FIELD_64
		
	}; 
	
	
	
	public static final AField[] PreAuthorizationReversalReqMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_14,
		FIELD_22,
		FIELD_25,
		FIELD_35,
		FIELD_36,
		FIELD_38,
		FIELD_39,
		FIELD_41,
		FIELD_42,
		FIELD_49,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		FIELD_64
		
	}; 
	
	
	public static final AField[] PreAuthorizationReversalResMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_12,
		FIELD_13,
		FIELD_14,
		FIELD_15,
		FIELD_25,
		FIELD_32,
		FIELD_37,
		FIELD_39,
		FIELD_41,
		FIELD_42,
		FIELD_49,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		FIELD_64
		
	}; 
	
	public static final AField[] PreAuthorizationCompleteReqMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_14,
		FIELD_22,
		FIELD_25,
		FIELD_26,
		FIELD_35,
		FIELD_36,
		FIELD_38,
		FIELD_41,
		FIELD_42,
		FIELD_49,
		FIELD_52,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		FIELD_GROUP_61_1_2_3,
		FIELD_64
	}; 
	
	
	public static final AField[] PreAuthorizationCompleteResMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_12,
		FIELD_13,
		FIELD_14,
		FIELD_15,
		FIELD_25,
		FIELD_32,
		FIELD_37,
		FIELD_38,
		FIELD_39,
		FIELD_41,
		FIELD_42,
		FIELD_44,
		FIELD_49,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		FIELD_GROUP_63_1_2_3_4,
		FIELD_64
		
		
	}; 
	
	
	public static final AField[] PreAuthorizationCompleteReversalReqMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_14,
		FIELD_22,
		FIELD_25,
		FIELD_35,
		FIELD_36,
		FIELD_38,
		FIELD_39,
		FIELD_41,
		FIELD_42,
		FIELD_49,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		FIELD_GROUP_61_1_2_3,
		FIELD_64
	}; 
	
	
	public static final AField[] PreAuthorizationCompleteReversalResMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_12,
		FIELD_13,
		FIELD_14,
		FIELD_15,
		FIELD_25,
		FIELD_32,
		FIELD_37,
		FIELD_39,
		FIELD_41,
		FIELD_42,
		FIELD_49,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		FIELD_64
		
		
	}; 
	
	
	public static final AField[] PreAuthorizationCancelReversalReqMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_14,
		FIELD_22,
		FIELD_25,
		FIELD_35,
		FIELD_36,
		FIELD_38,
		FIELD_39,
		FIELD_41,
		FIELD_42,
		FIELD_49,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		FIELD_GROUP_61_1_2_3,
		FIELD_64
	}; 
	
	
	public static final AField[] PreAuthorizationCancelReversalResMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_12,
		FIELD_13,
		FIELD_14,
		FIELD_15,
		FIELD_25,
		FIELD_32,
		FIELD_37,
		FIELD_39,
		FIELD_41,
		FIELD_42,
		FIELD_49,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		FIELD_64
		
		
	}; 
	
	
	public static final AField[] BalanceInquiryReqMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_11,
		FIELD_14,
		FIELD_22,
		FIELD_25,
		FIELD_26,
		FIELD_35,
		FIELD_36,
		FIELD_41,
		FIELD_42,
		FIELD_49,
		FIELD_52,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		FIELD_62_Identity,
		FIELD_64
	}; 
	
	
	public static final AField[] BalanceInquiryResMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_11,
		FIELD_12,
		FIELD_13,
		FIELD_14,
		FIELD_25,
		FIELD_32,
		FIELD_37,
		FIELD_39,
		FIELD_41,
		FIELD_42,
		FIELD_44,
		FIELD_49,
		FIELD_53,
		FIELD_54,
		FIELD_GROUP_60_1_2,
		FIELD_64
		
		
	}; 
	
	
	public static final AField[] PurchaseReversalReqMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_14,
		FIELD_22,
		FIELD_25,
		FIELD_35,
		FIELD_36,
		FIELD_38,
		FIELD_39,
		FIELD_41,
		FIELD_42,
		FIELD_49,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		FIELD_64
	}; 
	
	
	public static final AField[] PurchaseReversalResMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_12,
		FIELD_13,
		FIELD_14,
		FIELD_15,
		FIELD_25,
		FIELD_32,
		FIELD_37,
		FIELD_39,
		FIELD_41,
		FIELD_42,
		FIELD_44,
		FIELD_49,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		FIELD_64
	}; 
	
	
	
	public static final AField[] PurchaseCancelReqMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_14,
		FIELD_22,
		FIELD_25,
		FIELD_26,
		FIELD_35,
		FIELD_36,
		FIELD_37,
		FIELD_38,
		FIELD_41,
		FIELD_42,
		FIELD_49,
		FIELD_52,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		FIELD_GROUP_61_1_2,
		FIELD_64
	}; 
	
	
	public static final AField[] PurchaseCancelResMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_12,
		FIELD_13,
		FIELD_14,
		FIELD_15,
		FIELD_25,
		FIELD_32,
		FIELD_37,
		FIELD_38,
		FIELD_39,
		FIELD_41,
		FIELD_42,
		FIELD_44,
		FIELD_49,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		FIELD_GROUP_63_1_2_3_4,
		FIELD_64
	}; 
	
	
	
	public static final AField[] PurchaseCancelReversalReqMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_14,
		FIELD_22,
		FIELD_25,
		FIELD_35,
		FIELD_36,
		FIELD_38,
		FIELD_39,
		FIELD_41,
		FIELD_42,
		FIELD_49,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		FIELD_GROUP_61_1_2,
		FIELD_64
	}; 
	
	
	public static final AField[] PurchaseCancelReversalResMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_12,
		FIELD_13,
		FIELD_14,
		FIELD_15,
		FIELD_25,
		FIELD_32,
		FIELD_37,
		FIELD_39,
		FIELD_41,
		FIELD_42,
		FIELD_44,
		FIELD_49,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		FIELD_64
	}; 
	
	
	public static final AField[] RefundReqMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_14,
		FIELD_22,
		FIELD_25,
		FIELD_26,
		FIELD_35,
		FIELD_36,
		FIELD_37,
		FIELD_38,
		FIELD_41,
		FIELD_42,
		FIELD_49,
		FIELD_52,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		FIELD_GROUP_61_1_2_3,
		FIELD_GROUP_63_InternationalOrganizationCode,
		FIELD_64
	}; 
	
	
	public static final AField[] RefundResMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_12,
		FIELD_13,
		FIELD_14,
		FIELD_15,
		FIELD_25,
		FIELD_32,
		FIELD_37,
		FIELD_39,
		FIELD_41,
		FIELD_42,
		FIELD_44,
		FIELD_49,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		FIELD_GROUP_63_1_2_3_4,
		FIELD_64
		
		
	}; 
	
	

	public static final AField[] BatchSettlementReqMsg = new AField[]{
		FIELD_11,
		FIELD_41,
		FIELD_42,
		FIELD_GROUP_48_TOTAL,
		FIELD_49,
		FIELD_GROUP_60_1_2_3,
		FIELD_GROUP_63_OPCODE
	}; 
	
	
	public static final AField[]  BatchSettlementResMsg = new AField[]{
		FIELD_11,
		FIELD_12,
		FIELD_13,
		FIELD_15,
		FIELD_32,
		FIELD_37,
		FIELD_41,
		FIELD_42,
		FIELD_GROUP_48_TOTAL,
		FIELD_49,
		FIELD_GROUP_60_1_2_3,
		FIELD_GROUP_63_OPCODE
	}; 
	
	
	public static final AField[] BatchUploadReqMsg = new AField[]{
		FIELD_11,
		FIELD_41,
		FIELD_42,
		FIELD_48_DETIAL,
		FIELD_GROUP_60_1_2_3,
	}; 
	
	
	public static final AField[]  BatchUploadResMsg = new AField[]{
		FIELD_11,
		FIELD_12,
		FIELD_13,
		FIELD_32,
		FIELD_37,
		FIELD_39,
		FIELD_41,
		FIELD_42,
		FIELD_48_DETIAL,
		FIELD_GROUP_60_1_2_3,
	};

	
	
	public static final AField[] SettlementAdjustTransactionResMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_12,
		FIELD_13,
		FIELD_14,
		FIELD_15,
		FIELD_25,
		FIELD_32,
		FIELD_37,
		FIELD_38,
		FIELD_39,
		FIELD_41,
		FIELD_42,
		FIELD_44,
		FIELD_49,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		FIELD_GROUP_63_1_2_3_4,
		FIELD_64
		
	};
	
	
	
	
	public static final AField[] SettlementAdjustTransactionReqMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_14,
		FIELD_22,
		FIELD_25,
		FIELD_38,
		FIELD_41,
		FIELD_42,
		FIELD_48_TIP_AMOUNT,
		FIELD_49,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		FIELD_GROUP_61_1_2_3_4_5,
		FIELD_63_1,
		FIELD_64
		
	};
	
	public static final AField[] NotifySettlementAdjustResMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_12,
		FIELD_13,
		FIELD_14,
		FIELD_15,
		FIELD_25,
		FIELD_32,
		FIELD_37,
		FIELD_38,
		FIELD_39,
		FIELD_41,
		FIELD_42,
		FIELD_44,
		FIELD_49,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		FIELD_GROUP_63_1_2_3_4
	};
	
	
	
	public static final AField[] NotifySettlementAdjustReqMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_14,
		FIELD_22,
		FIELD_25,
		FIELD_38,
		FIELD_41,
		FIELD_42,
		FIELD_48_TIP_AMOUNT,
		FIELD_49,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		FIELD_GROUP_61_1_2_3_4_5,
		FIELD_63_1,
	};
	
	
	
	public static final AField[] NotifySettlementResMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_12,
		FIELD_13,
		FIELD_14,
		FIELD_15,
		FIELD_25,
		FIELD_32,
		FIELD_37,
		FIELD_38,
		FIELD_39,
		FIELD_41,
		FIELD_42,
		FIELD_44,
		FIELD_49,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		FIELD_GROUP_63_1_2_3_4
	};
	
	
	
	public static final AField[] NotifySettlementReqMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_14,
		FIELD_22,
		FIELD_25,
		FIELD_38,
		FIELD_41,
		FIELD_42,
		FIELD_48_TIP_AMOUNT,
		FIELD_49,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		FIELD_GROUP_61_1_2_3_4_5,
		FIELD_63_1
	};
	
	public static final AField[] SettlementTransactionResMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_12,
		FIELD_13,
		FIELD_14,
		FIELD_15,
		FIELD_25,
		FIELD_32,
		FIELD_37,
		FIELD_38,
		FIELD_39,
		FIELD_41,
		FIELD_42,
		FIELD_44,
		FIELD_49,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		FIELD_GROUP_63_1_2_3_4,
		FIELD_64
		
	};
	
	
	
	public static final AField[] SettlementTransactionReqMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_14,
		FIELD_22,
		FIELD_25,
		FIELD_38,
		FIELD_41,
		FIELD_42,
		FIELD_48_TIP_AMOUNT,
		FIELD_49,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		FIELD_GROUP_61_1_2_3_4_5,
		FIELD_GROUP_63_InternationalOrganizationCode,
		FIELD_64
		};
		
	public static final AField[] NotifyRefundReqMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_14,
		FIELD_22,
		FIELD_25,
		FIELD_26,
		FIELD_35,
		FIELD_36,
		FIELD_37,
		FIELD_38,
		FIELD_41,
		FIELD_42,
		FIELD_49,
		FIELD_52,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		FIELD_GROUP_61_1_2_3,
		FIELD_GROUP_63_InternationalOrganizationCode
	}; 
	
	
	public static final AField[] NotifyRefundResMsg = new AField[]{
		FIELD_2,
		FIELD_3,
		FIELD_4,
		FIELD_11,
		FIELD_12,
		FIELD_13,
		FIELD_14,
		FIELD_15,
		FIELD_25,
		FIELD_32,
		FIELD_37,
		FIELD_39,
		FIELD_41,
		FIELD_42,
		FIELD_44,
		FIELD_49,
		FIELD_53,
		FIELD_GROUP_60_1_2,
		FIELD_GROUP_63_1_2_3_4
	};
	
	private static final AField FIELD_BCD_GROUP_48_TOTAL = new BCDGroupField(48,62,3,null,false);
	private static final AField FIELD_GROUP_BCD_60_1_2_3 = new BCDGroupField(60,11,3,null,false);
	private static final AField FIELD_GROUP_BCD_61_1_2_3_4_5 = new BCDGroupField(61,29,3,null,false);
	private static final AField FIELD_ASCII_GROUP_63_1_2_3_4 = new ASCIIGroupField(63,63,3,null,false);
	
	
	public static final AField[] ExcessTable = new AField[]{
		FIELD_2, 
		FIELD_3, 
		FIELD_4, 
		FIELD_11,
		FIELD_12, 
		FIELD_13, 
		FIELD_14,
		FIELD_15,
		FIELD_22, 
		FIELD_25, 
		FIELD_26, 
		FIELD_32, 
		FIELD_35, 
		FIELD_36, 
		FIELD_37, 
		FIELD_38, 
		FIELD_39, 
		FIELD_41,
		FIELD_42, 
		FIELD_43, 
		FIELD_44,
		FIELD_BCD_GROUP_48_TOTAL,
		FIELD_49, 
		FIELD_52, 
		FIELD_53, 
		FIELD_54,
		FIELD_GROUP_BCD_60_1_2_3,
		FIELD_GROUP_BCD_61_1_2_3_4_5,
		//XXX 62域有可能是终端密钥和终端状态信息的参数，目前只处理了是参数的情况
		FIELD_62_Parameter, 
		FIELD_ASCII_GROUP_63_1_2_3_4, 
		FIELD_64, 

	};
	
	
	/**
	 * QCUP消息结构
	 */
	private static final AField FIELD_QCUP_2 = 	new MutableASCIIField(2,"主账号", 19, false, "qcup_PrimaryAccountNumber",2,true);
	private static final AField FIELD_QCUP_3 = 	new ASCIIField(3,"交易处理码", 6, false, "qcup_ProcessingCode",true);
	private static final AField FIELD_QCUP_4 = 	new ASCIIField(4,"交易金额", 12, false, "qcup_TransactionsAmount",ISO8583Util.PREFIX,true);
	private static final AField FIELD_QCUP_7 = 	new ASCIIField(7,"交易传输时间", 10, false, "qcup_TransmissionDateAndTime",true);
	private static final AField FIELD_QCUP_11 = new ASCIIField(11,"系统跟踪号", 6, false, "qcup_SystemTraceAuditNumber",true);
	private static final AField FIELD_QCUP_12 = new ASCIIField(12,"受卡方所在地时间", 6, false, "qcup_LocalTransactionTime",false);
	private static final AField FIELD_QCUP_13 = new ASCIIField(13,"受卡方所在地日期", 4, false, "qcup_LocalTransactionDate",false);
	private static final AField FIELD_QCUP_14 = new ASCIIField(14,"卡有效期", 4, false, "qcup_ExpirationDate",false);
	private static final AField FIELD_QCUP_15 = new ASCIIField(15,"清算日期", 4, false, "qcup_SettlementDate",false);
	private static final AField FIELD_QCUP_18 = new ASCIIField(18,"商户类型", 4, false, "qcup_MerchantType",true);
	private static final AField FIELD_QCUP_22 = new ASCIIField(22,"服务点输入方式码", 3, false, "qcup_PointOfServiceEntryModeCode",false);
	private static final AField FIELD_QCUP_23 = new ASCIIField(23,"卡序列号", 3, false, "qcup_CardSequenceNumber",false);
	private static final AField FIELD_QCUP_25 = new ASCIIField(25,"服务点条件码", 2, false, "qcup_PointOfServiceConditionMode",true);
	private static final AField FIELD_QCUP_26 = new ASCIIField(26,"服务点PIN获取码", 2, false, "qcup_PointofServicePinCaptureCode",false);
	private static final AField FIELD_QCUP_32 = new MutableASCIIField(32,"受理机构标识码", 11, false, "qcup_AcquiringInstitutionIdentificationCode",2,true);
	private static final AField FIELD_QCUP_33 = new MutableASCIIField(33,"发送机构标识码", 11, false, "qcup_ForwardingInstitutionIdentificationCode",2,true);
	private static final AField FIELD_QCUP_35 = new MutableASCIIField(35,"第二磁道数据", 37, false, "qcup_Track2Data",2,false);
	private static final AField FIELD_QCUP_36 = new MutableASCIIField(36,"第三磁道数据", 104, false, "qcup_Track3Data",3,false);
	private static final AField FIELD_QCUP_37 = new ASCIIField(37,"检索参考号", 12, false, "qcup_RetrievalReferenceNumber",false);
	private static final AField FIELD_QCUP_38 = new ASCIIField(38,"授权标识应答码", 6, false, "qcup_AuthorizationIdentificationResponse",true);
	private static final AField FIELD_QCUP_39 = new ASCIIField(39,"应答码", 2, false, "qcup_ResponseCode",true);
	private static final AField FIELD_QCUP_41 = new ASCIIField(41,"受卡机终端标识码", 8, false, "qcup_CardAcceptorTerminalIdentification",true);
	private static final AField FIELD_QCUP_42 = new ASCIIField(42,"受卡方标识码", 15, false, "qcup_CardAcceptorIdentificationCode",true);
	private static final AField FIELD_QCUP_43 = new ASCIIField(43,"受卡方名称地址", 40, false, "qcup_CardAcceptorNameLocation",false);
	private static final AField FIELD_QCUP_48 = new MutableASCIIField(48,"自定义域", 600, false, "qcup_AdditionalDataPrivate",3,false);
	private static final AField FIELD_QCUP_49 = new ASCIIField(49,"交易货币代码", 3, false, "qcup_CurrencyCodeTransaction",false);
	private static final AField FIELD_QCUP_52 = new BinaryField(52, "个人标识数据码", 8, false, "qcup_pinData",false);
	private static final AField FIELD_QCUP_53 = new ASCIIField(53,"安全控制信息", 16, false, "qcup_SecurityRelatedControlInformation",false);
	private static final AField FIELD_QCUP_54 = new MutableASCIIField(54,"实际余额", 40, false, "qcup_AdditionalAmounts",3,false);
	private static final AField FIELD_QCUP_55 = new MutableBinaryField(55, "基于PBOC借贷记标准的IC卡数据域", 255, false, "qcup_IntegratedCircuitCardSystemRelatedData",3,false);
	private static final AField FIELD_QCUP_59 = new MutableASCIIField(59,"明细查询数据", 600, false, "qcup_DetailInquiring",3,false);
	private static final AField FIELD_QCUP_60 = new MutableASCIIField(60,"自定义域", 100, false, "qcup_AdditionalPointOfServiceInformation",3,false);
	private static final AField FIELD_QCUP_61 = new MutableASCIIField(61,"持卡人身份认证信息", 255, false, "qcup_AuthenticationInformation",3,false);
	private static final AField FIELD_QCUP_63 = new MutableASCIIField(63,"自定义域", 512, false, "qcup_ReservedPrivate",3,false);
	private static final AField FIELD_QCUP_70 = new ASCIIField(70,"网络管理码", 3, false, "qcup_Networkmanagementcode",false);
	private static final AField FIELD_QCUP_90 = new ASCIIField(90,"原始数据元", 42, false, "qcup_OriginalDataElements",true);
	private static final AField FIELD_QCUP_96 = new BinaryField(96, "报文安全码", 8, false, "qcup_MessageSecurityCode",false);
	private static final AField FIELD_QCUP_100 = new MutableASCIIField(100,"接收机构标识码", 11, false, "qcup_DestinationInstituitionIdentificationCode",2,false);
	private static final AField FIELD_QCUP_102 = new MutableASCIIField(102,"账户标识1", 28, false, "qcup_AccountIdentification1",2,false);
	private static final AField FIELD_QCUP_103 = new MutableASCIIField(103,"账户标识2", 28, false, "qcup_AccountIdentification2",2,false);
	private static final AField FIELD_QCUP_128 = new BinaryField(128,"报文鉴别码", 8, false, "qcup_Mac",false);
	
	
	/**
	 * 多渠道渠道签到(请求)
	 */
	public static final AField[] channelInReqMsg = new AField[]{
//		FIELD_QCUP_7,
		FIELD_QCUP_11,
		FIELD_QCUP_12,
		FIELD_QCUP_13,
		FIELD_QCUP_33,
//		FIELD_QCUP_41,
		FIELD_QCUP_70
	};
	
	/**
	 * 多渠道渠道签到(返回)
	 */
	public static final AField[] channelInResMsg = new AField[]{
		FIELD_QCUP_11,
		FIELD_QCUP_12,
		FIELD_QCUP_13,
		FIELD_QCUP_33,
		FIELD_QCUP_39,
		FIELD_QCUP_48,
		FIELD_QCUP_70
	};
	
	/**
	 * 多渠道委托关系查询(请求)
	 */
	public static final AField[] TrustRelationshipQueryReqMsg = new AField[]{
		FIELD_QCUP_2,
		FIELD_QCUP_3,
		FIELD_QCUP_7,
		FIELD_QCUP_11,
		FIELD_QCUP_12,
		FIELD_QCUP_13,
		FIELD_QCUP_22,
		FIELD_QCUP_25,
		FIELD_QCUP_32,
		FIELD_QCUP_33,
		FIELD_QCUP_37,
		FIELD_QCUP_41,
		FIELD_QCUP_42,
		FIELD_QCUP_43,
		FIELD_QCUP_48,
		FIELD_QCUP_49,
		FIELD_QCUP_128
	};
	
	/**
	 * 多渠道委托关系查询(返回)
	 */
	public static final AField[] TrustRelationshipQueryResMsg = new AField[]{
		FIELD_QCUP_2,
		FIELD_QCUP_3,
		FIELD_QCUP_7,
		FIELD_QCUP_11,
		FIELD_QCUP_12,
		FIELD_QCUP_13,
		FIELD_QCUP_25,
		FIELD_QCUP_32,
		FIELD_QCUP_33,
		FIELD_QCUP_37,
		FIELD_QCUP_39,
		FIELD_QCUP_41,
		FIELD_QCUP_42,
		FIELD_QCUP_48,
		FIELD_QCUP_49,
		FIELD_QCUP_100
	};
	
	/**
	 * 多渠道单笔代收(请求)
	 */
	public static final AField[] SingleCollectingReqMsg = new AField[]{
		FIELD_QCUP_2,
		FIELD_QCUP_3,
		FIELD_QCUP_4,
		FIELD_QCUP_7,
		FIELD_QCUP_11,
		FIELD_QCUP_12,
		FIELD_QCUP_13,
		FIELD_QCUP_22,
		FIELD_QCUP_25,
		FIELD_QCUP_32,
		FIELD_QCUP_33,
		FIELD_QCUP_37,
		FIELD_QCUP_41,
		FIELD_QCUP_42,
		FIELD_QCUP_43,
		FIELD_QCUP_48,
		FIELD_QCUP_49,
		FIELD_QCUP_128
	};
	
	/**
	 * 多渠道单笔代收(返回)
	 */
	public static final AField[] SingleCollectingResMsg = new AField[]{
		FIELD_QCUP_2,
		FIELD_QCUP_3,
		FIELD_QCUP_4,
		FIELD_QCUP_7,
		FIELD_QCUP_11,
		FIELD_QCUP_12,
		FIELD_QCUP_13,
		FIELD_QCUP_15,
		FIELD_QCUP_25,
		FIELD_QCUP_32,
		FIELD_QCUP_33,
		FIELD_QCUP_37,
		FIELD_QCUP_39,
		FIELD_QCUP_41,
		FIELD_QCUP_42,
		FIELD_QCUP_48,
		FIELD_QCUP_49,
		FIELD_QCUP_59,
		FIELD_QCUP_100,
		FIELD_QCUP_128
	};
	
	/**
	 * 多渠道单笔代收撤销(请求)
	 */
	public static final AField[] SingleCollectingCancelReqMsg = new AField[]{
		FIELD_QCUP_2,
		FIELD_QCUP_3,
		FIELD_QCUP_4,
		FIELD_QCUP_7,
		FIELD_QCUP_11,
		FIELD_QCUP_12,
		FIELD_QCUP_13,
		FIELD_QCUP_22,
		FIELD_QCUP_25,
		FIELD_QCUP_32,
		FIELD_QCUP_33,
		FIELD_QCUP_37,
		FIELD_QCUP_41,
		FIELD_QCUP_42,
		FIELD_QCUP_43,
		FIELD_QCUP_48,
		FIELD_QCUP_49,
		FIELD_QCUP_90,
		FIELD_QCUP_128
	};
	
	/**
	 * 多渠道单笔代收撤销(返回)
	 */
	public static final AField[] SingleCollectingCancelResMsg = new AField[]{
		FIELD_QCUP_2,
		FIELD_QCUP_3,
		FIELD_QCUP_4,
		FIELD_QCUP_7,
		FIELD_QCUP_11,
		FIELD_QCUP_12,
		FIELD_QCUP_13,
		FIELD_QCUP_15,
		FIELD_QCUP_25,
		FIELD_QCUP_32,
		FIELD_QCUP_33,
		FIELD_QCUP_37,
		FIELD_QCUP_39,
		FIELD_QCUP_41,
		FIELD_QCUP_42,
		FIELD_QCUP_48,
		FIELD_QCUP_49,
		FIELD_QCUP_59,
		FIELD_QCUP_100,
		FIELD_QCUP_128
	};
	
	/**
	 * 多渠道冲正(请求)
	 */
	public static final AField[] ReverseReqMsg = new AField[]{
		FIELD_QCUP_2,
		FIELD_QCUP_3,
		FIELD_QCUP_4,
		FIELD_QCUP_7,
		FIELD_QCUP_11,
		FIELD_QCUP_12,
		FIELD_QCUP_13,
		FIELD_QCUP_25,
		FIELD_QCUP_32,
		FIELD_QCUP_33,
		FIELD_QCUP_37,
		FIELD_QCUP_41,
		FIELD_QCUP_42,
		FIELD_QCUP_43,
		FIELD_QCUP_48,
		FIELD_QCUP_49,
		FIELD_QCUP_90,
		FIELD_QCUP_128
	};
	
	
	/**
	 * 多渠道冲正(返回)
	 */
	public static final AField[] ReverseResMsg = new AField[]{
		FIELD_QCUP_2,
		FIELD_QCUP_3,
		FIELD_QCUP_4,
		FIELD_QCUP_7,
		FIELD_QCUP_11,
		FIELD_QCUP_12,
		FIELD_QCUP_13,
		FIELD_QCUP_15,
		FIELD_QCUP_25,
		FIELD_QCUP_32,
		FIELD_QCUP_33,
		FIELD_QCUP_37,
		FIELD_QCUP_39,
		FIELD_QCUP_41,
		FIELD_QCUP_42,
		FIELD_QCUP_48,
		FIELD_QCUP_49,
		FIELD_QCUP_59,
		FIELD_QCUP_100,
		FIELD_QCUP_128
	};
	
	
	/**
	 * 多渠道委托关系建立/撤销(请求)
	 */
	public static final AField[] TrustRelationshipAddOrDeleteReqMsg = new AField[]{
		FIELD_QCUP_2,
		FIELD_QCUP_3,
		FIELD_QCUP_7,
		FIELD_QCUP_11,
		FIELD_QCUP_12,
		FIELD_QCUP_13,
		FIELD_QCUP_22,
		FIELD_QCUP_25,
		FIELD_QCUP_26,
		FIELD_QCUP_32,
		FIELD_QCUP_33,
		FIELD_QCUP_37,
		FIELD_QCUP_41,
		FIELD_QCUP_42,
		FIELD_QCUP_43,
		FIELD_QCUP_48,
		FIELD_QCUP_49,
		FIELD_QCUP_128
	};
	
	/**
	 * 多渠道委托关系建立/撤销(返回)
	 */
	public static final AField[] TrustRelationshipAddOrDeleteResMsg = new AField[]{
		FIELD_QCUP_2,
		FIELD_QCUP_3,
		FIELD_QCUP_7,
		FIELD_QCUP_11,
		FIELD_QCUP_12,
		FIELD_QCUP_13,
		FIELD_QCUP_15,
		FIELD_QCUP_25,
		FIELD_QCUP_32,
		FIELD_QCUP_33,
		FIELD_QCUP_37,
		FIELD_QCUP_39,
		FIELD_QCUP_41,
		FIELD_QCUP_42,
		FIELD_QCUP_48,
		FIELD_QCUP_49,
		FIELD_QCUP_128
	};
	
	
	/**
	 * 多渠道实时代付(请求)
	 */
	public static final AField[] payForAnotherReqMsg = new AField[]{
		FIELD_QCUP_2,
		FIELD_QCUP_3,
		FIELD_QCUP_4,
		FIELD_QCUP_7,
		FIELD_QCUP_11,
		FIELD_QCUP_12,
		FIELD_QCUP_13,
		FIELD_QCUP_22,
		FIELD_QCUP_25,
		FIELD_QCUP_32,
		FIELD_QCUP_33,
		FIELD_QCUP_37,
		FIELD_QCUP_41,
		FIELD_QCUP_42,
		FIELD_QCUP_43,
		FIELD_QCUP_48,
		FIELD_QCUP_49,
		FIELD_QCUP_61,
		FIELD_QCUP_128
	};
	
	/**
	 * 多渠道实时代付(返回)
	 */
	public static final AField[] payForAnotherResMsg = new AField[]{
		FIELD_QCUP_2,
		FIELD_QCUP_3,
		FIELD_QCUP_4,
		FIELD_QCUP_7,
		FIELD_QCUP_11,
		FIELD_QCUP_12,
		FIELD_QCUP_13,
		FIELD_QCUP_15,
//		FIELD_QCUP_22,
		FIELD_QCUP_25,
		FIELD_QCUP_32,
		FIELD_QCUP_33,
		FIELD_QCUP_37,
		FIELD_QCUP_39,
		FIELD_QCUP_41,
		FIELD_QCUP_42,
		FIELD_QCUP_48,
		FIELD_QCUP_49,
		FIELD_QCUP_100,
		FIELD_QCUP_128
	};
	
	/**
	 * 多渠道代付确认(请求)
	 */
	public static final AField[] payForAnotherConfirmReqMsg = new AField[]{
		FIELD_QCUP_2,
		FIELD_QCUP_3,
		FIELD_QCUP_4,
		FIELD_QCUP_7,
		FIELD_QCUP_11,
		FIELD_QCUP_12,
		FIELD_QCUP_13,
		FIELD_QCUP_22,
		FIELD_QCUP_25,
		FIELD_QCUP_32,
		FIELD_QCUP_33,
		FIELD_QCUP_37,
		FIELD_QCUP_41,
		FIELD_QCUP_42,
		FIELD_QCUP_43,
		FIELD_QCUP_48,
		FIELD_QCUP_49,
		FIELD_QCUP_90,
		FIELD_QCUP_128
	};
	
	/**
	 * 多渠道代付确认(返回)
	 */
	public static final AField[] payForAnotherConfirmResMsg = new AField[]{
		FIELD_QCUP_2,
		FIELD_QCUP_3,
		FIELD_QCUP_4,
		FIELD_QCUP_7,
		FIELD_QCUP_11,
		FIELD_QCUP_12,
		FIELD_QCUP_13,
		FIELD_QCUP_15,
//		FIELD_QCUP_22,
		FIELD_QCUP_25,
		FIELD_QCUP_32,
		FIELD_QCUP_33,
		FIELD_QCUP_37,
		FIELD_QCUP_39,
		FIELD_QCUP_41,
		FIELD_QCUP_42,
		FIELD_QCUP_48,
		FIELD_QCUP_49,
//		FIELD_QCUP_90,
//		FIELD_QCUP_100,
		FIELD_QCUP_128
	};
	
	/**
	 * 银行卡账户验证(请求)
	 */
	public static final AField[] checkAccountReqMsg = new AField[]{
		FIELD_QCUP_2,
		FIELD_QCUP_3,
		FIELD_QCUP_7,
		FIELD_QCUP_11,
		FIELD_QCUP_12,
		FIELD_QCUP_13,
		FIELD_QCUP_22,
		FIELD_QCUP_25,
		FIELD_QCUP_32,
		FIELD_QCUP_33,
		FIELD_QCUP_37,
		FIELD_QCUP_41,
		FIELD_QCUP_42,
		FIELD_QCUP_43,
		FIELD_QCUP_48,
		FIELD_QCUP_49,
		FIELD_QCUP_61,
		FIELD_QCUP_128
	};
	
	/**
	 * 银行卡账户验证(返回)
	 */
	public static final AField[] checkAccountResMsg = new AField[]{
		FIELD_QCUP_2,
		FIELD_QCUP_3,
		FIELD_QCUP_7,
		FIELD_QCUP_11,
		FIELD_QCUP_12,
		FIELD_QCUP_13,
		FIELD_QCUP_14,
		FIELD_QCUP_15,
//		FIELD_QCUP_18,
//		FIELD_QCUP_22,
		FIELD_QCUP_25,
		FIELD_QCUP_32,
		FIELD_QCUP_33,
		FIELD_QCUP_37,
		FIELD_QCUP_39,
		FIELD_QCUP_41,
		FIELD_QCUP_42,
		FIELD_QCUP_48,
		FIELD_QCUP_49,
		FIELD_QCUP_61,
		FIELD_QCUP_100,
		FIELD_QCUP_128
	};
	
	/**
	 * 缴费(请求)
	 */
	public static final AField[] payReqMsg = new AField[]{
		FIELD_QCUP_2,
		FIELD_QCUP_3,
		FIELD_QCUP_4,
		FIELD_QCUP_7,
		FIELD_QCUP_11,
		FIELD_QCUP_12,
		FIELD_QCUP_13,
		FIELD_QCUP_15,
		FIELD_QCUP_18,
		FIELD_QCUP_22,
		FIELD_QCUP_25,
		FIELD_QCUP_32,
		FIELD_QCUP_33,
		FIELD_QCUP_37,
		FIELD_QCUP_41,
		FIELD_QCUP_42,
		FIELD_QCUP_48,
		FIELD_QCUP_49,
//		FIELD_QCUP_59,
		FIELD_QCUP_60,
		FIELD_QCUP_100,
		FIELD_QCUP_103,
		FIELD_QCUP_128
	};
	
	/**
	 * 缴费(返回)
	 */
	public static final AField[] payResMsg = new AField[]{
		FIELD_QCUP_2,
		FIELD_QCUP_3,
		FIELD_QCUP_4,
		FIELD_QCUP_7,
		FIELD_QCUP_11,
		FIELD_QCUP_12,
		FIELD_QCUP_13,
		FIELD_QCUP_15,
		FIELD_QCUP_18,
		FIELD_QCUP_22,
		FIELD_QCUP_25,
		FIELD_QCUP_32,
		FIELD_QCUP_33,
		FIELD_QCUP_37,
		FIELD_QCUP_39,
		FIELD_QCUP_41,
		FIELD_QCUP_42,
		FIELD_QCUP_48,
		FIELD_QCUP_49,
//		FIELD_QCUP_59,
		FIELD_QCUP_60,
		FIELD_QCUP_103,
		FIELD_QCUP_128
	};
	
	/**
	 * 缴费撤销(请求)
	 */
	public static final AField[] payCancleReqMsg = new AField[]{
		FIELD_QCUP_2,
		FIELD_QCUP_3,
		FIELD_QCUP_4,
		FIELD_QCUP_7,
		FIELD_QCUP_11,
		FIELD_QCUP_12,
		FIELD_QCUP_13,
		FIELD_QCUP_15,
		FIELD_QCUP_18,
		FIELD_QCUP_25,
		FIELD_QCUP_32,
		FIELD_QCUP_33,
		FIELD_QCUP_37,
		FIELD_QCUP_41,
		FIELD_QCUP_42,
		FIELD_QCUP_43,
		FIELD_QCUP_48,
		FIELD_QCUP_49,
		FIELD_QCUP_59,
		FIELD_QCUP_60,
		FIELD_QCUP_90,
		FIELD_QCUP_103,
		FIELD_QCUP_128
	};
	
	/**
	 * 缴费撤销(返回)
	 */
	public static final AField[] payCancleResMsg = new AField[]{
		FIELD_QCUP_2,
		FIELD_QCUP_3,
		FIELD_QCUP_4,
		FIELD_QCUP_7,
		FIELD_QCUP_11,
		FIELD_QCUP_12,
		FIELD_QCUP_13,
		FIELD_QCUP_15,
		FIELD_QCUP_18,
//		FIELD_QCUP_22,
		FIELD_QCUP_25,
		FIELD_QCUP_32,
		FIELD_QCUP_33,
		FIELD_QCUP_37,
		FIELD_QCUP_39,
		FIELD_QCUP_41,
		FIELD_QCUP_42,
		FIELD_QCUP_48,
		FIELD_QCUP_49,
		FIELD_QCUP_59,
		FIELD_QCUP_60,
//		FIELD_QCUP_63,
//		FIELD_QCUP_100,
		FIELD_QCUP_103,
		FIELD_QCUP_128
	};
	
	/**
	 * 重置密钥(请求)
	 */
	public static final AField[] resetKeyReqMsg = new AField[]{
		FIELD_QCUP_7,
		FIELD_QCUP_11,
		FIELD_QCUP_33,
		FIELD_QCUP_53,
		FIELD_QCUP_70,
		FIELD_QCUP_96,
		FIELD_QCUP_128
	};
	
	/**
	 * 重置密钥(返回)
	 */
	public static final AField[] resetKeyResMsg = new AField[]{
		FIELD_QCUP_7,
		FIELD_QCUP_11,
		FIELD_QCUP_33,
		FIELD_QCUP_39,
		FIELD_QCUP_53,
		FIELD_QCUP_70,
		FIELD_QCUP_128
	};
	
	/**
	 * 多渠道实时消费(请求)
	 */
	public static final AField[] payForConsumerReqMsg = new AField[]{
		FIELD_QCUP_2,
		FIELD_QCUP_3,
		FIELD_QCUP_4,
		FIELD_QCUP_7,
		FIELD_QCUP_11,
		FIELD_QCUP_12,
		FIELD_QCUP_13,
		FIELD_QCUP_22,
		FIELD_QCUP_25,
		FIELD_QCUP_32,
		FIELD_QCUP_33,
		FIELD_QCUP_37,
		FIELD_QCUP_41,
		FIELD_QCUP_42,
		FIELD_QCUP_43,
		FIELD_QCUP_48,
		FIELD_QCUP_49,
		FIELD_QCUP_61,
		FIELD_QCUP_128
	};
	
	/**
	 * 多渠道实时消费(请求)
	 */
	public static final AField[] payForConsumerReqMsgHasPin = new AField[]{
		FIELD_QCUP_2,
		FIELD_QCUP_3,
		FIELD_QCUP_4,
		FIELD_QCUP_7,
		FIELD_QCUP_11,
		FIELD_QCUP_12,
		FIELD_QCUP_13,
		FIELD_QCUP_22,
		FIELD_QCUP_25,
		FIELD_QCUP_26,
		FIELD_QCUP_32,
		FIELD_QCUP_33,
		FIELD_QCUP_37,
		FIELD_QCUP_41,
		FIELD_QCUP_42,
		FIELD_QCUP_43,
		FIELD_QCUP_48,
		FIELD_QCUP_49,
		FIELD_QCUP_52,
		FIELD_QCUP_53,
		FIELD_QCUP_60,
		FIELD_QCUP_61,
		FIELD_QCUP_128
	};
	
	/**
	 * 多渠道实时消费(返回)
	 */
	public static final AField[] payForConsumerResMsg = new AField[]{
		FIELD_QCUP_2,
		FIELD_QCUP_3,
		FIELD_QCUP_4,
		FIELD_QCUP_7,
		FIELD_QCUP_11,
		FIELD_QCUP_12,
		FIELD_QCUP_13,
		FIELD_QCUP_14,
		FIELD_QCUP_15,
//		FIELD_QCUP_22,
		FIELD_QCUP_25,
		FIELD_QCUP_32,
		FIELD_QCUP_33,
		FIELD_QCUP_37,
		FIELD_QCUP_39,
		FIELD_QCUP_41,
		FIELD_QCUP_42,
		FIELD_QCUP_48,
		FIELD_QCUP_49,
//		FIELD_QCUP_100,
		FIELD_QCUP_128
	};
	
	/**
	 * 多渠道实时消费-磁条卡(请求)
	 */
	public static final AField[] payForConsumerCitiaoReqMsg = new AField[]{
		FIELD_QCUP_2,
		FIELD_QCUP_3,
		FIELD_QCUP_4,
		FIELD_QCUP_7,
		FIELD_QCUP_11,
		FIELD_QCUP_12,
		FIELD_QCUP_13,
		FIELD_QCUP_22,
		FIELD_QCUP_25,
		FIELD_QCUP_26,
		FIELD_QCUP_32,
		FIELD_QCUP_33,
		FIELD_QCUP_35,
//		FIELD_QCUP_36,
		FIELD_QCUP_37,
		FIELD_QCUP_41,
		FIELD_QCUP_42,
		FIELD_QCUP_43,
//		FIELD_QCUP_48,
		FIELD_QCUP_49,
//		FIELD_QCUP_52,
//		FIELD_QCUP_53,
		FIELD_QCUP_60,
		FIELD_QCUP_128
	};
	
	/**
	 * 多渠道实时消费-磁条卡(返回)
	 */
	public static final AField[] payForConsumerCitiaoResMsg = new AField[]{
		FIELD_QCUP_2,
		FIELD_QCUP_3,
		FIELD_QCUP_4,
		FIELD_QCUP_7,
		FIELD_QCUP_11,
		FIELD_QCUP_12,
		FIELD_QCUP_13,
		FIELD_QCUP_14,
		FIELD_QCUP_15,
//		FIELD_QCUP_22,
		FIELD_QCUP_25,
		FIELD_QCUP_32,
		FIELD_QCUP_33,
		FIELD_QCUP_37,
		FIELD_QCUP_38,
		FIELD_QCUP_39,
		FIELD_QCUP_41,
		FIELD_QCUP_42,
//		FIELD_QCUP_48,
		FIELD_QCUP_49,
//		FIELD_QCUP_52,
//		FIELD_QCUP_53,
		FIELD_QCUP_128
	};
	
	/**
	 * 多渠道实时消费-IC卡(请求)
	 */
	public static final AField[] payForConsumerICReqMsg = new AField[]{
		FIELD_QCUP_2,
		FIELD_QCUP_3,
		FIELD_QCUP_4,
		FIELD_QCUP_7,
		FIELD_QCUP_11,
		FIELD_QCUP_12,
		FIELD_QCUP_13,
		FIELD_QCUP_22,
//		FIELD_QCUP_23,
		FIELD_QCUP_25,
		FIELD_QCUP_26,
		FIELD_QCUP_32,
		FIELD_QCUP_33,
//		FIELD_QCUP_35,
//		FIELD_QCUP_36,
		FIELD_QCUP_37,
		FIELD_QCUP_41,
		FIELD_QCUP_42,
		FIELD_QCUP_43,
//		FIELD_QCUP_48,
		FIELD_QCUP_49,
//		FIELD_QCUP_52,
//		FIELD_QCUP_53,
		FIELD_QCUP_55,
		FIELD_QCUP_60,
		FIELD_QCUP_128
	};
	
	/**
	 * 多渠道实时消费-ic卡(返回)
	 */
	public static final AField[] payForConsumerICResMsg = new AField[]{
		FIELD_QCUP_2,
		FIELD_QCUP_3,
		FIELD_QCUP_4,
		FIELD_QCUP_7,
		FIELD_QCUP_11,
		FIELD_QCUP_12,
		FIELD_QCUP_13,
		FIELD_QCUP_14,
		FIELD_QCUP_15,
		FIELD_QCUP_23,
		FIELD_QCUP_25,
		FIELD_QCUP_32,
		FIELD_QCUP_33,
		FIELD_QCUP_37,
		FIELD_QCUP_38,
		FIELD_QCUP_39,
		FIELD_QCUP_41,
		FIELD_QCUP_42,
//		FIELD_QCUP_48,
		FIELD_QCUP_49,
		FIELD_QCUP_55,
		FIELD_QCUP_128
	};
	
	/**
	 * 多渠道消费撤销(请求)
	 */
	public static final AField[] payForConsumerRevokeReqMsg = new AField[]{
		FIELD_QCUP_2,
		FIELD_QCUP_3,
		FIELD_QCUP_4,
		FIELD_QCUP_7,
		FIELD_QCUP_11,
		FIELD_QCUP_12,
		FIELD_QCUP_13,
		FIELD_QCUP_22,
		FIELD_QCUP_25,
		FIELD_QCUP_32,
		FIELD_QCUP_33,
		FIELD_QCUP_37,
		FIELD_QCUP_41,
		FIELD_QCUP_42,
		FIELD_QCUP_43,
//		FIELD_QCUP_48,
		FIELD_QCUP_49,
		FIELD_QCUP_90,
		FIELD_QCUP_128
	};
	
	/**
	 * 多渠道消费撤销(返回)
	 */
	public static final AField[] payForConsumerRevokeResMsg = new AField[]{
		FIELD_QCUP_2,
		FIELD_QCUP_3,
		FIELD_QCUP_4,
		FIELD_QCUP_7,
		FIELD_QCUP_11,
		FIELD_QCUP_12,
		FIELD_QCUP_13,
		FIELD_QCUP_14,
		FIELD_QCUP_15,
		FIELD_QCUP_25,
		FIELD_QCUP_32,
		FIELD_QCUP_33,
		FIELD_QCUP_37,
		FIELD_QCUP_39,
		FIELD_QCUP_41,
		FIELD_QCUP_42,
		FIELD_QCUP_48,
		FIELD_QCUP_49,
		FIELD_QCUP_128
	};
	
	/**
	 * 多渠道消费退货(请求)
	 */
	public static final AField[] payForConsumerTuiHuoReqMsg = new AField[]{
		FIELD_QCUP_2,
		FIELD_QCUP_3,
		FIELD_QCUP_4,
		FIELD_QCUP_7,
		FIELD_QCUP_11,
		FIELD_QCUP_12,
		FIELD_QCUP_13,
		FIELD_QCUP_22,
		FIELD_QCUP_25,
		FIELD_QCUP_32,
		FIELD_QCUP_33,
		FIELD_QCUP_37,
		FIELD_QCUP_41,
		FIELD_QCUP_42,
		FIELD_QCUP_43,
		FIELD_QCUP_48,
		FIELD_QCUP_49,
		FIELD_QCUP_90,
		FIELD_QCUP_128
	};
	
	/**
	 * 多渠道消费退货(返回)
	 */
	public static final AField[] payForConsumerTuiHuoResMsg = new AField[]{
		FIELD_QCUP_2,
		FIELD_QCUP_3,
		FIELD_QCUP_4,
		FIELD_QCUP_7,
		FIELD_QCUP_11,
		FIELD_QCUP_12,
		FIELD_QCUP_13,
		FIELD_QCUP_14,
		FIELD_QCUP_15,
		FIELD_QCUP_25,
		FIELD_QCUP_32,
		FIELD_QCUP_33,
		FIELD_QCUP_37,
		FIELD_QCUP_39,
		FIELD_QCUP_41,
		FIELD_QCUP_42,
		FIELD_QCUP_48,
		FIELD_QCUP_49,
		FIELD_QCUP_128
	};
	
	/**
	 * 多渠道冲正(请求)
	 */
	public static final AField[] payForConsumerCorrectReqMsg = new AField[]{
		FIELD_QCUP_2,
		FIELD_QCUP_3,
		FIELD_QCUP_4,
		FIELD_QCUP_7,
		FIELD_QCUP_11,
		FIELD_QCUP_12,
		FIELD_QCUP_13,
		FIELD_QCUP_25,
		FIELD_QCUP_32,
		FIELD_QCUP_33,
		FIELD_QCUP_37,
		FIELD_QCUP_41,
		FIELD_QCUP_42,
		FIELD_QCUP_43,
		FIELD_QCUP_48,
		FIELD_QCUP_49,
		FIELD_QCUP_90,
		FIELD_QCUP_128
	};
	
	/**
	 * 多渠道冲正(返回)
	 */
	public static final AField[] payForConsumerCorrectResMsg = new AField[]{
		FIELD_QCUP_2,
		FIELD_QCUP_3,
		FIELD_QCUP_4,
		FIELD_QCUP_7,
		FIELD_QCUP_11,
		FIELD_QCUP_12,
		FIELD_QCUP_13,
		FIELD_QCUP_15,
		FIELD_QCUP_25,
		FIELD_QCUP_32,
		FIELD_QCUP_33,
		FIELD_QCUP_37,
		FIELD_QCUP_39,
		FIELD_QCUP_41,
		FIELD_QCUP_42,
		FIELD_QCUP_48,
		FIELD_QCUP_49,
		FIELD_QCUP_128
	};
	
	
	private MessageStructure() {

	}
	
	/**
	 * @brief 获得消息结构
	 * 
	 * @param msg
	 *            消息
	 * @return
	 */
	public static AField[] getFields(String uniqueID) {
		return (AField[]) map.get(uniqueID);
	}
	
	@SuppressWarnings("unchecked")
	public static void put(String uniqueID, AField[] msgFieldTable) {
		map.put(uniqueID,msgFieldTable);
	}
	
}
