package zqing.textmining.output;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class TextExporter extends BaseExporter
{

	public TextExporter()
	{
		super();
	}

	public void Export(String fileName, String txt)
	{
		try
		{
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(fileName), "GB2312");
			BufferedWriter bwriter = new BufferedWriter(writer);
			bwriter.write(txt);
			bwriter.close();
		} catch (Exception e)
		{
			System.out.println("д����ļ�����");
			e.printStackTrace();
		}
	}

	public void Export(String fileName, String[] lines)
	{
		try
		{
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(fileName), "GB2312");
			BufferedWriter bwriter = new BufferedWriter(writer);
			for (String s : lines)
			{
				bwriter.write(s + "\n");
			}
			bwriter.close();
		} catch (Exception e)
		{
			System.out.println("д����ļ�����");
			e.printStackTrace();
		}
	}

}
