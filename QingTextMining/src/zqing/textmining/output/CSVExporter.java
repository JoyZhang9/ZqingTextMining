package zqing.textmining.output;

import java.util.TreeMap;

import zqing.textmining.entity.WordEntity;

public class CSVExporter extends BaseExporter
{

	public CSVExporter()
	{
		super();
	}
	
	/*
	 * ����ı��е�CSV�ļ�
	 */
	public long ExportLines(String[] lines)
	{
		ExportLinesHeader();
		ExportLinesBody();
		ExportLinesFooter();
		return -1;		
	}
	
	private long ExportLinesHeader()
	{
		return -1;
	}
	
	private long ExportLinesBody()
	{
		return -1;
	}
	
	private long ExportLinesFooter()
	{
		return -1;
	}
	
	/*
	 * �����ͳ�ƾ���CSV�ļ�
	 */
	public long ExportWords(TreeMap<String, WordEntity> words)
	{
		return -1;
	}

}
