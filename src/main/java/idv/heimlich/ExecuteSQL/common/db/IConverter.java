package idv.heimlich.ExecuteSQL.common.db;


public interface IConverter<Po> {

	Po convert(RowMap paramDataObject);

}
