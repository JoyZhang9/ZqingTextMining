package zqing.textmining;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import edu.fudan.nlp.cn.Sentenizer;
import edu.fudan.nlp.cn.tag.CWSTagger;
import zqing.textmining.config.Configuration;
import zqing.textmining.entity.WordEntity;
import zqing.textmining.input.CSVReader;
import zqing.textmining.output.ExcelExporter;
import zqing.textmining.output.TextExporter;

public class MainTestApp
{
	public static void main(String[] args)
	{
		try
		{
			Configuration cfg = Configuration.getInstance();
			cfg.ParseArgs(args); // ���������������������Ϣ����Configuration�����С�
			TextMining txtMining = new TextMining();

			CSVReader csvReader = new CSVReader();
			csvReader.LoadFromFile(cfg.SourceFileName);

			String[] txtSrcLines = csvReader.GetFieldsByColumn(2); // ���CSV�ı��ĵڶ��е��ı����ݡ�
			String[] txtConnectedLines = txtMining.ConnectBrokenLines(txtSrcLines);
			String txtSrc = txtMining.GetConnectedString(txtConnectedLines);
			
			//�ø������Դ�����еĶϾ������жϾ�
			Sentenizer.addPuncs('\n');
			String[] txtLines = Sentenizer.split(txtSrc);

//			//������ı������д���ı��ļ���
//			TextExporter txtExporter = new TextExporter();
//			txtExporter.Export(cfg.ResultFileName, txtLines);
			
			//������ı������д��Excel�ļ���
			ExcelExporter excelExp = new ExcelExporter(cfg.ResultFileName);
			excelExp.ExportLines(txtLines);
			
			// �ø������Դ�����ÿ����зִ�
			CWSTagger tag = new CWSTagger("./models/seg.m");
			excelExp.AddSheet();
			HashMap<String, WordEntity> WordsDict = new HashMap<String, WordEntity>();
			int iRow = 0;
			for(String s:txtConnectedLines)
			{	
				if(s.trim().isEmpty())
					continue;
				String[] words = tag.tag2Array(s);
				excelExp.ExportFieldsInOneRow(words, iRow);
				iRow++;

				//��Ƶͳ��
				for(String w:words)
				{
					if(!(w.trim().isEmpty()))
					{
						if(WordsDict.containsKey(w))
						{
							WordEntity wEntity = WordsDict.get(w);
							wEntity.Count++;
						}
						else
						{
							WordsDict.put(w, new WordEntity(w,1));
						}						
					}					
				}
			}
			
			// �����Ƶͳ�ƽ��
			excelExp.AddSheet();
			Iterator<Entry<String, WordEntity>> iter = WordsDict.entrySet().iterator();
			iRow = 0;
			while (iter.hasNext()) 
			{
				Entry<String, WordEntity> entry = iter.next();
				String[] results = new String[2]{entry.getKey(),  (entry.getValue().Count) };
				//Label l = entry.getKey();
			Object val = entry.getValue();
			}
			 
			excelExp.Close();

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
