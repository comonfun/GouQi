package bind;

public class ItemRequestConverter {
    public ItemRequest toItemRequest(ItemViewModel viewModel){
        return new ItemRequest(
                viewModel.getTypem(),
                viewModel.getNumm(),
                viewModel.getDatem(),
                viewModel.getBackupm()
        );
    }
}
